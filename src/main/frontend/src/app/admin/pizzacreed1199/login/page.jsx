'use client';
import Link from 'next/link';
import React, { useState } from 'react';
import { useRouter } from "next/navigation";
import { toast } from 'react-hot-toast';

export default function LoginPage() {
    const router = useRouter();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    // State variable to handle login message
    const [loginMessage, setLoginMessage] = useState('');

    async function handleLogin(e) {
        e.preventDefault();
        try {
            toast.dismiss();
            await toast.promise(
                new Promise(async (resolve, reject) => {
                    try {
                        const response = await fetch('http://localhost:8080/api/auth/pizzacreed/adminlogin', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify({ username, password }),
                            credentials: 'include' // Include credentials (cookies)
                        });

                        if (response.status === 401) {
                            setLoginMessage('Incorrect username or password');
                            reject(new Error('Incorrect username or password'));
                            return;
                        }

                        const result = await response.json();
                        console.log({ result });

                        if (response.ok && result.code === '00') {
                            router.push('/');
                            router.refresh();
                            setLoginMessage('Signed in successfully!');
                            resolve(result);
                        } else {
                            const errorMessage = result.message || 'Login failed';
                            setLoginMessage(errorMessage);
                            reject(new Error(errorMessage));
                        }
                    } catch (networkError) {
                        setLoginMessage('Network error: Failed to connect to the server');
                        reject(new Error('Network error: Failed to connect to the server'));
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
            console.error('Login error:', error);
            setLoginMessage(error.message);
        }
    }

    return (
        <main className="bg-[url('/img/p4.jpg')] bg-cover">
            <div className='bg-[#000000bd]'>
                <div className="relative flex flex-col items-center justify-center min-h-screen overflow-hidden">
                    <div className="w-full p-6 bg-black rounded-md shadow-md lg:max-w-xl">
                        <Link href="/admin/pizzacreed1199/login">
                            <h1 className="text-3xl font-bold text-center text-gray-700">
                                <span className="text-orange-400">Pizza Creed</span> - <span className="text-orange-200">Admin Login</span>
                            </h1>
                        </Link>
                        <form className="mt-6" onSubmit={handleLogin}>
                            <div className="mb-4">
                                <label htmlFor="username" className="block text-sm font-semibold text-gray-600">
                                    Username
                                </label>
                                <input
                                    type="text"
                                    name="username"
                                    id="username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    placeholder="username"
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
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    placeholder="********"
                                    className="block w-full px-4 py-2 mt-2 text-orange-900 bg-white border rounded-md focus:border-orange-400 focus:ring-orange-300 focus:outline-none focus:ring focus:ring-opacity-40"
                                />
                            </div>
                            <Link href="/forget" className="text-xs text-orange-600 hover:underline">
                                Forget Password?
                            </Link>
                            <div className="mt-2 mb-4">
                                <button
                                    className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-orange-500 rounded-md hover:bg-orange-600 focus:outline-none focus:bg-orange-700">
                                    Login
                                </button>
                            </div>
                        </form>

                        {/* Display login message */}
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
