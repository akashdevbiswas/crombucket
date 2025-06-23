import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";
import SideNavbar from "../components/SideNavbar";

const Home = () => {
  return (
    <div className="min-h-screen p-[1px] h-[100vh]">
      <header className="mt-[20px] horizontal-layout">
        <Navbar />
      </header>
      <main className="mt-[20px] flex horizontal-layout h-10/12 gap-4">
        <div className="w-[20%] h-full">
          <SideNavbar />
        </div>
        <div className="w-full h-full">
          <Outlet />
        </div>
      </main>
    </div>
  );
};

export default Home;
