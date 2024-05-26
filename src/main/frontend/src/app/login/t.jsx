'use client';

import Link from "next/link"
import { signIn } from 'next-auth/react';
import { useRouter } from "next/navigation";
import { toast } from 'react-hot-toast';
import Logo from '../../../public/logo-no-background.png'
import Image from "next/image";

export default function LoginPage() {
    const router = useRouter();

    async function handleLogin(e) {
        e.preventDefault();
        try {
            const formData = new FormData(e.currentTarget);
            toast.dismiss();
            await toast.promise(
                new Promise(async (resolve, reject) => {
                    const response = await signIn('credentials', {
                        email: formData.get('email'),
                        password: formData.get('password'),
                        redirect: false,
                    });

                    console.log({ response });

                    if (!response?.error) {
                        // Redirect user to the home page if sign-in is successful
                        router.push('/');
                        router.refresh();
                        resolve(response); // Resolve the promise if sign-in is successful
                    } else {
                        // Reject the promise if there's an error during sign-in
                        if (response.status == 401) {
                            reject(new Error("Incorrect username or password"));
                        }
                        reject(new Error(response.error));

                    }
                }),
                {
                    loading: 'Signing in...',
                    success: 'Signed in successfully!',
                    error: (err) => `${err.message}`,
                },
                {
                    style: {
                        minWidth: '250px',
                    },
                }
            );


        } catch (error) {
            // Handle any errors that occur during sign-in or promise handling
            //toast.error(error.message, { duration: 5000 });

            console.error(error.message)
        }
    }

    return (
        <section className="bg-[#030014]">
            <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:mt-3 lg:py-0">



                <div className="w-full rounded-lg border md:mt-0 sm:max-w-md xl:p-0 bg-white/5 shadow-lg bg-opacity-50 backdrop-blur-2xl border-gray-700">
                    <Image
                        src={Logo}
                        alt='logo'
                        height={35}
                        className='cursor-pointer flex items-center justify-center mt-5 p-2 mx-auto'
                    />
                    <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1 className="text-xl font-bold leading-tight tracking-tight md:text-2xl text-white">
                            Login
                        </h1>
                        <form className="space-y-4 md:space-y-6" onSubmit={handleLogin}>
                            <div>
                                <label htmlFor="email" className="block mb-2 text-sm font-medium text-white">Your email</label>
                                <input type="email" name="email" id="email" className="border sm:text-sm rounded-lg block w-full p-2.5 bg-gray-700 border-gray-600 placeholder-gray-400 text-white focus:ring-blue-500 focus:border-blue-500" placeholder="name@company.com" required />
                            </div>
                            <div>
                                <label htmlFor="password" className="block mb-2 text-sm font-medium text-white">Password</label>
                                <input type="password" name="password" id="password" placeholder="••••••••" className=" border sm:text-sm rounded-lg block w-full p-2.5 bg-gray-700 border-gray-600 placeholder-gray-400 text-white focus:ring-blue-500 focus:border-blue-500" required />
                            </div>

                            <button type="submit" className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center  dark:focus:ring-primary-800">Login</button>
                            <p className="text-sm font-light text-gray-500 dark:text-gray-400">
                                Don&apos;t have an account yet? <Link href="/register" className="font-medium text-primary-500 hover:underline">Register here </Link>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    )
}