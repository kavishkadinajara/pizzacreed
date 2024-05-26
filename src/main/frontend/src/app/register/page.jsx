'use client';

import React, { useState } from 'react';
import { useRouter } from "next/navigation";
import Link from "next/link";
import toast from "react-hot-toast";

export default function RegisterPage() {
  const router = useRouter();
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleConfirmPasswordChange = (event) => {
    setConfirmPassword(event.target.value);
  };

  const createAccount = async (event) => {
    event.preventDefault();

    if (password !== confirmPassword) {
      setErrorMessage("Passwords do not match");
      setPassword(''); // Clear password field
      setConfirmPassword(''); // Clear confirm password field
      return;
    }

    setErrorMessage("");
    const formData = new FormData(event.target);

    try {
      const response = await fetch('https://x8c0cgkj-8080.asse.devtunnels.ms/api/pizzacreed/auth/register', {
        method: 'POST',
        body: JSON.stringify({
          customerName: formData.get('customerName'),
          customerAddress: formData.get('customerAddress'),
          customerTele: formData.get('customerTele'),
          customerEmail: formData.get('customerEmail'),
          password: formData.get('password'),

        }),
        headers: {
          'Content-Type': 'application/json',
        },
      });

      const responseData = await response.json();

      if (!response.ok) {
        throw new Error(responseData.message || 'Failed to create account');
      }

      if (responseData.code === '00' && responseData.message === 'Success') {
        router.push('/login');
        toast.success('Signed up successfully!');
      } else {
        throw new Error(responseData.message || 'Unknown error occurred');
      }
    } catch (error) {
      console.error('Error occurred:', error);
      toast.error(error.message || 'An unexpected error occurred');
      setErrorMessage(error.message || 'An unexpected error occurred');
    }
  };

  return (
    <main className="bg-[url('/img/p11.jpg')] bg-cover">
      <div className='bg-[#000000bd]'>
        <div className="relative flex justify-center py-8">
          <div style={{ minWidth: "40%" }}>
            <div className="flex shadow-lg flex-1 flex-col rounded-3xl justify-center px-6 py-12 lg:px-8 bg-black">
              <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                <Link href="/">
                  <h1 className="text-3xl font-bold text-center text-gray-700">
                    <span className="text-orange-400">Pizza</span>-<span className="text-orange-200">Creed</span>
                  </h1>
                </Link>
                <h2 className="mt-1 text-center text-2xl font-bold leading-9 tracking-tight text-gray-400">
                  Create a new account
                </h2>
              </div>

              <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form className="space-y-6" onSubmit={createAccount}>
                  <div>
                    <label htmlFor="customerName" className="block text-md font-medium leading-6 text-gray-600">
                      Name
                    </label>
                    <div className="mt-2">
                      <input id="customerName" name="customerName" type="text" autoComplete="name" required className="block w-full rounded-md border-0 py-1.5 px-2 text-black shadow-sm ring-1 ring-inset ring-orange-400 placeholder:text-black focus:ring-2 focus:ring-inset focus:ring-orange-600 sm:text-sm sm:leading-6" />
                    </div>
                  </div>

                  <div>
                    <label htmlFor="customerAddress" className="block text-md font-medium leading-6 text-gray-600">
                      Address
                    </label>
                    <div className="mt-2">
                      <input id="customerAddress" name="customerAddress" type="text" autoComplete="address" required className="block w-full rounded-md border-0 py-1.5 px-2 text-black shadow-sm ring-1 ring-inset ring-orange-400 placeholder:text-black focus:ring-2 focus:ring-inset focus:ring-orange-600 sm:text-sm sm:leading-6" />
                    </div>
                  </div>

                  <div>
                    <label htmlFor="customerEmail" className="block text-md font-medium leading-6 text-gray-600">
                      Email address
                    </label>
                    <div className="mt-2">
                      <input id="customerEmail" name="customerEmail" type="email" autoComplete="customerEmail" required className="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-orange-400 px-2 placeholder:text-black focus:ring-2 focus:ring-inset focus:ring-orange-600 sm:text-sm sm:leading-6" />
                    </div>
                  </div>

                  <div>
                    <label htmlFor="customerTele" className="block text-md font-medium leading-6 text-gray-600">
                      Telephone
                    </label>
                    <div className="mt-2">
                      <input id="customerTele" name="customerTele" type="tel" autoComplete="customerTele" required className="block w-full rounded-md border-0 py-1.5 px-2 text-black shadow-sm ring-1 ring-inset ring-orange-400 placeholder:text-black focus:ring-2 focus:ring-inset focus:ring-orange-600 sm:text-sm sm:leading-6" />
                    </div>
                  </div>

                  <div>
                    <div className="flex items-center justify-between">
                      <label htmlFor="password" className="block text-md font-medium leading-6 text-gray-600">
                        Password
                      </label>
                    </div>
                    <div className="mt-2">
                      <input id="password" name="password" type="password" autoComplete="current-password" value={password} onChange={handlePasswordChange} required className="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-orange-400 placeholder:text-black px-2 focus:ring-2 focus:ring-inset focus:ring-orange-600 sm:text-sm sm:leading-6" />
                    </div>
                  </div>

                  <div>
                    <div className="flex items-center justify-between">
                      <label htmlFor="confirmpassword" className="block text-md font-medium leading-6 text-gray-600">
                        Confirm Password
                      </label>
                    </div>
                    <div className="mt-2">
                      <input id="confirmpassword" name="confirmpassword" type="password" autoComplete="current-password" value={confirmPassword} onChange={handleConfirmPasswordChange} required className="block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-orange-400 px-2 placeholder:text-black focus:ring-2 focus:ring-inset focus:ring-orange-600 sm:text-sm sm:leading-6" />
                    </div>
                  </div>
                  {errorMessage && <p className='text-red-600 text-sm'>{errorMessage}</p>}
                  <div className=''>
                    <button type="submit" className="flex w-full justify-center rounded-md bg-orange-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-orange-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-600">
                      Sign in
                    </button>
                  </div>
                </form>

                <p className="mt-10 text-center text-sm text-gray-500">
                  Already have an account?{" "}
                  <Link href={'/login'}>
                    <span className="font-semibold leading-6 text-orange-600 hover:text-orange-500 cursor-pointer">
                      Sign In
                    </span>
                  </Link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}
