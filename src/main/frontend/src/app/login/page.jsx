'use client';

import Link from "next/link"
import { signIn } from 'next-auth/react';
import { useRouter } from "next/navigation";
import { toast } from 'react-hot-toast';
import { useState } from 'react';

export default function LoginPage() {
    const router = useRouter();
    const [loginMessage, setLoginMessage] = useState('');

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
            setLoginMessage(error.message);
            console.error(error.message)
        }
    }

    return (
        <main className="bg-[url('/img/p7.jpg')] bg-cover">
            <div className='bg-[#000000bd]'>
                <div className="relative flex flex-col items-center justify-center min-h-screen overflow-hidden">
                    <div className="w-full p-6 bg-black rounded-md shadow-md lg:max-w-xl">
                        <Link href="/">
                            <h1 className="text-3xl font-bold text-center text-gray-700">
                                <span className="text-orange-400">Pizza</span> - <span className="text-orange-200">Creed </span>
                            </h1>
                        </Link>
                        <form className="mt-6" onSubmit={handleLogin}>
                            <div className="mb-4">
                                <label htmlFor="email" className="block text-sm font-semibold text-gray-600">
                                    Email
                                </label>
                                <input
                                    type="email"
                                    name="email"
                                    id="email"
                                    placeholder="john@example.com"
                                    className="block w-full px-4 py-2 mt-2 text-orange-900 bg-white border rounded-md focus:border-orange-400 focus:ring-orange-300 focus:outline-none focus:ring focus:ring-opacity-40"
                                />
                            </div>
                            <div className="mb-2">
                                <label htmlFor="password" className="block text-sm font-semibold text-gray-600">
                                    Password
                                </label>
                                <input
                                    type="password"
                                    name="password"
                                    id="password"
                                    placeholder="********"
                                    className="block w-full px-4 py-2 mt-2 text-orange-900 bg-white border rounded-md focus:border-orange-400 focus:ring-orange-300 focus:outline-none focus:ring focus:ring-opacity-40"
                                />
                            </div>
                            <Link href="/register" className="text-xs text-orange-600 hover:underline">
                                Not Register?
                            </Link>
                            <div className="mt-2 mb-4">
                                <button type='submit'
                                    className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-orange-500 rounded-md hover:bg-orange-600 focus:outline-none focus:bg-orange-700">
                                    Login
                                </button>
                            </div>
                        </form>

                        {loginMessage && (
                            <div className={`mt-4 text-sm text-center ${loginMessage === 'Signed in successfully!' ? 'text-orange-600' : 'text-red-600'}`}>
                                {loginMessage}
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </main>
    );
}
