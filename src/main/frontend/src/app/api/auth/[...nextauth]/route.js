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
        token.authId = user.id;
        token.username = user.username;
      }
      return token;
    },
    async session({ session, token }) {
      session.user.id = token.id;
      session.user.username = token.username;
      session.user = token.user;
      console.log("Session: " + session);
      console.log("Token:" + token);
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
            credentials: 'include',
          });
          console.log("Res:" + res);
          console.log("Cre:" + credentials);


          const data = await res.json();
          console.log("DATA : " + data);
          console.log("DATA : " + data.authId + " --> " + data.content.username  + "-->" + data.content.password);

          if (res.ok && data.code === '00') {
            const user = {
              id: data.content.authId,
              username: data.content.username,
              
            };
            console.log(user);
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
