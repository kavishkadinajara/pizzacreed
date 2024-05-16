import NavBar from '@/components/NavBar';

export default function ShopLayout({children,}) {
    return (
      <>
          <NavBar />
          {children}

      </>
    );
  }