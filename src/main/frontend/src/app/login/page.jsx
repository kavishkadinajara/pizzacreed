'use client';
import Link from 'next/link'
import React, { useState } from 'react';
import { signIn } from 'next-auth/react';
import { useRouter } from "next/navigation";
import { toast } from 'react-hot-toast';

export default function LoginPage() {
    const router = useRouter();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    // State variables to handle login status and message
    const [loginStatus, setLoginStatus] = useState(null);
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
            //toast.error(error.message, { duration: 5000 });

            console.error(error.message);
           // loginMessage(error.message);
            

        }
    }
    
    return (
        <main className="bg-[url('/img/p7.jpg')] bg-cover">
            <div className='bg-[#000000bd]'>
                <div className="relative flex flex-col items-center justify-center min-h-screen overflow-hidden">
                    <div className="w-full p-6 bg-black rounded-md shadow-md lg:max-w-xl">
                        <Link href="/">
                        <h1 className="text-3xl font-bold text-center text-gray-700">
                            <span className="text-orange-400">Pizza</span>-<span className="text-orange-200">Creed</span>
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
                                    id='email'
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
                                    name='password'
                                    id='password'
                                    placeholder="********"
                                    className="block w-full px-4 py-2 mt-2 text-orange-900 bg-white border rounded-md focus:border-orange-400 focus:ring-orange-300 focus:outline-none focus:ring focus:ring-opacity-40"
                                />

                            </div>
                            <Link href="/forget" className="text-xs text-orange-600 hover:underline">
                            Forget Password?
                            </Link>
                            <div className="mt-2">
                                <button
                                    className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-orange-500 rounded-md hover:bg-orange-600 focus:outline-none focus:bg-orange-700">
                                    Login
                                </button>
                            </div>
                        </form>

                        {/* Display login status and message */}
                        {loginStatus !== null && (
                            <div className={`mt-4 text-sm text-center ${loginStatus ? 'text-orange-600' : 'text-red-600'}`}>
                                {loginMessage}
                            </div>
                        )} 


                        <p className="mt-4 text-sm text-center text-gray-700">
                            Don't have an account?{" "}
                            <Link href="/register" className="font-medium text-orange-600 hover:underline">
                            Sign up
                            </Link>
                        </p>
                    </div>
                </div>    
            </div>  
        </main>
    )
}


// const [email, setEmail] = useState('');
//     const [password, setPassword] = useState('');

//     // State variables to handle login status and message
//     const [loginStatus, setLoginStatus] = useState(null);
//     const [loginMessage, setLoginMessage] = useState('');

//     // Function to handle the login process
// const handleLogin = async () => {
//     try {
//         // Construct the URL with query parameters
//         const url = `/api/customers?p_email=${encodeURIComponent(email)}&p_password=${encodeURIComponent(password)}`;

//         // Make a GET request to the API endpoint
//         const response = await fetch(url, {
//             method: 'GET',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//         });
            
//                 // Parse the response as JSON
//     const data = await response.json();

//     // Print the retrieved data to the console
//     console.log('Retrieved data:', data);


//             // Check the response status
//             if (response.ok) {
//                 const data = await response.json();
//                 // Set the login status and message based on the API response
//                 setLoginStatus(data.success);
//                 setLoginMessage(data.message);

//                 if (data.success) {
//                     // Redirect to the home page after successful login
//                     router.push('/');
//                 }
                
//             } else {
//                 // Handle the case where the API request was not successful
//                 setLoginStatus(false);
//                 setLoginMessage('Login failed');
//             }
//         } catch (error) {
//             console.error('Error during login:', error);
//             setLoginStatus(false);
//             setLoginMessage('Internal server error');
//         }