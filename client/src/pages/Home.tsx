import { Fragment } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";

const Home = () => {
  return (
    <Fragment>
      <header className="mt-[20px]">
        <Navbar />
      </header>
      <Outlet />
    </Fragment>
  );
};

export default Home;
