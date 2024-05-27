import NextAuth from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';
import { compare } from 'bcrypt';
import { query } from '@/lib/db';
import { getToken } from "next-auth/jwt"

const handler = NextAuth({
  
  session: {
    strategy: 'jwt',
  },
  pages: {
    signIn: '/login',
  },
  callbacks:{
    async jwt({token, user}) {
      //console.log("JWT: " + JSON.stringify(user));
      if (user) {
        token.id = user.id;
        token.name = user.name;
        token.email = user.email;
      }
      return token;
    },
    async session({session, token}) {
      session.user = token.user;
      //console.log("SESSION: " ,session);
      return session;
    },
  },
  providers: [
    CredentialsProvider({
      credentials: {
        email: {},
        password: {},
      },
      async authorize(credentials, req) {

        console.log(credentials)
         const response = await query({
            query: "SELECT * FROM customer WHERE customer_email= ?;",
            values: [credentials?.email]
         })
         
         console.log(response)
        const user = response[0];

        if (!user) {
          return null; // Return null if user not found
        }

        const passwordCorrect = await compare(
          credentials?.password || '',
          user.password
        );

        console.log({ passwordCorrect });

        if (passwordCorrect) {
    
          return {
            id: user.id,
            name: user.name,
            email: user.email,
          };
        }

        return null;
      },
    }),
  ],
});

export { handler as GET, handler as POST };