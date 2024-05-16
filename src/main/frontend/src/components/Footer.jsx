import React from 'react';
import Link from 'next/link';

const Footer = () => {
return (
<div>
   
    <footer className="mx-20 py-8">
    <hr className='mb-5' />
        <div className="container mx-auto">
            <div className="flex flex-col md:flex-row justify-between items-center">
                <Link href="/">
                <h1 className="text-3xl font-bold  text-gray-700">
                    <span className="text-orange-400">Pizza</span>-<span className="text-orange-200">Creed</span>
                </h1>
                <p className='text-md'>Made with ❤️ by Kavishka Dinajara</p>
                </Link>
                <div className="text-white mb-4 md:mb-0">Copyright © 2024 | Pizza-Creed. All Rights reserved. </div>
            </div>
        </div>
    </footer>
</div>
);
};

export default Footer;