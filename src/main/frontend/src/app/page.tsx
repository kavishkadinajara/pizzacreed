import Image from "next/image";
import NavBar from "@/components/NavBar"
import  {HeroSection}  from '@/components/HeroSection'
import Footer from "@/components/Footer"
import Service from "@/components/Service"

export default function Home() {
  return (
    <main className="">
      {/* <NavBar /> */}
      <HeroSection />
      <Service />
      <Footer />
    </main>
  );
}
