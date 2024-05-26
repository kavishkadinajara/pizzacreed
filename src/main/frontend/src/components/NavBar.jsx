import React from 'react';
import Image from 'next/image';
import Link from 'next/link';
import { ShoppingCartIcon, UserCircleIcon } from '@heroicons/react/24/outline';
import { getServerSession } from 'next-auth';
import Logout from './Logout';

export default async function NavBar() {
  const session = await getServerSession();
  console.log('session:' + session);
  return (
    <header className="px-4 md:px-5 py-1 text-white shadow-lg">
      <nav className="container mx-auto flex flex-wrap items-center justify-between py-1">
        {/* Logo */}
        <Link href={'/'} className='flex items-center space-x-2'>
          <div>
            <Image 
                src={"/logos/logo-3.png"}
                width={72}
                height={75}>
            </Image>
          </div>
          <div className='text-3xl font-bold mt-4 text-orange-800'>
            <span className="text-orange-600">Pizza</span>-<span className="text-orange-500">Creed</span>
          </div>
        </Link>

        {/* Navigation Links */}
        <ul className="flex flex-wrap justify-center md:justify-start items-center space-x-8 md:space-x-12 text-orange-300 mt-4 md:mt-0">
          <li>
            <Link href="/" className="hover:text-orange-500">Home</Link>
          </li>
          <li>
            <Link href="/menu" className="hover:text-orange-500">Menu</Link>
          </li>
          <li>
            <Link href="/#service-area" className="hover:text-orange-500">Services</Link>
          </li>
          <li>
            <Link href="/#reviewSection" className="hover:text-orange-500">Contact Us</Link>
          </li>
        </ul>
        
        {/* Login/Logout button */}
        <div className='flex flex-wrap justify-center md:justify-start mt-4 md:mt-0'>   
          {session ? (
            <>
            <div className='flex gap-3'>
              <Link href='/account' className='hover:underline-offset-2 hover:underline my-auto'>
                <span className='my-auto'>
                  <UserCircleIcon className='h-7 my-auto' />
                </span>
              </Link> 
              <Link href='/product'>
                <ShoppingCartIcon className='h-7 my-auto mt-2' />
              </Link>
              <button className='bg-orange-500 py-2 px-6 rounded-lg mr-4 hover:bg-orange-500 shadow-lg hover:shadow-orange-500'><Logout/></button>
            </div>
            </>
          ) : (
            <div className='flex gap-4'>
              <Link href="/login" className="text-xl text-orange-500 mr-5 focus:text-orange-500 hover:font-semibold shadow-lg hover:">Login</Link>
            </div>
          )}
        </div>
      </nav>
    </header>
  );
}
