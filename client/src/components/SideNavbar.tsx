import { NavLink } from "react-router-dom";

const SideNavbar = () => {

  const navLinks = [
    {
      name: "Dashboard",
      path: "/",
    },
    {
      name: "Files",
      path: "/files",
    },
    {
      name: "Storage Information",
      path: "/storage-information",
    },
  ];

  return (
    <div className="flex flex-col w-full h-full">
      <div className="h-2/6 flex items-center bg-primary rounded-2xl">
        <h2 className="w-fit text-5xl font-semibold text-white text-center">
          Admin Console
        </h2>
      </div>
      <div className="flex flex-col justify-center h-full nav-links gap-4">
        {navLinks.map(({ name, path}) => (
          <NavLink to= {path} key={name} className={({ isActive }) => (isActive ? "active" : "")}>{name}</NavLink>
        ))}
      </div>
    </div>
  );
};

export default SideNavbar;
