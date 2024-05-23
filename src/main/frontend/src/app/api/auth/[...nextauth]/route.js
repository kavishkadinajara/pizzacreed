import NextAuth from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';

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
        token.username = user.username;
      }
      console.log("token" + token);
      return token;
    },
    async session({ session, token }) {
      session.user = {
        id: token.id,
        username: token.username,
      };
      return session;
    },
  },
  providers: [
    CredentialsProvider({
      credentials: {
        username: { label: "Username", type: "text" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        try {
          const res = await fetch('http://localhost:8080/api/pizzacreed/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(credentials),
          });

          const data = await res.json();

          if (res.ok && data.code === '00') {
            const user = {
              id: data.content.authId,
              username: data.content.username,
            };
            console.log("user" + user);
            return user;
          } else {
            return null;
          }
        } catch (error) {
          console.error('Login error:', error);
          return null;
        }
      },
    }),
  ],
});

export { handler as GET, handler as POST };
