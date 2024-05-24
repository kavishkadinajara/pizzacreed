import NextAuth from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';
import { compare } from 'bcrypt';
import { query } from '@/lib/db';

const handler = NextAuth({
  session: {
    strategy: 'jwt',
  },
  pages: {
    signIn: '/login',
  },
  callbacks: {
    async jwt({ token, user }) {
      if (user) {
        token.id = user.id;
        token.name = user.name;
        token.username = user.username;
      }
      return token;
    },
    async session({ session, token }) {
      session.user = { id: token.id, name: token.name, username: token.username };
      return session;
    },
  },
  providers: [
    CredentialsProvider({
      credentials: {
        username: { label: "Username", type: "text" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials, req) {
        console.log("Credentials:", credentials);
        
        const response = await query({
          query: "SELECT * FROM auth WHERE username= ?;",
          values: [credentials?.username]
        });
        
        console.log("Database response:", response);

        const user = response[0];

        if (!user) {
          console.log("User not found");
          return null;
        }

        const passwordCorrect = await compare(credentials?.password || '', user.password);

        console.log("Password correct:", passwordCorrect);

        if (passwordCorrect) {
          return {
            id: user.id,
            name: user.name,
            username: user.username,
          };
        }

        console.log("Invalid credentials");
        return null;
      },
    }),
  ],
});

export { handler as GET, handler as POST };
