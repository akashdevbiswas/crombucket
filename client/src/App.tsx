import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Auth from "./pages/Auth";
import Home from "./pages/Home";
import Error from "./pages/Error";
import DashBoard from "./pages/DashBoard";
import Files from "./pages/Files";
import StorageInformation from "./pages/StorageInformation";

const routes = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    errorElement: <Error/>,
    children: [
      {
        index:true,
        element: <DashBoard/>
      },
      {
        path: "files",
        element: <Files/>
      },
      {
        path: "storage-information",
        element: <StorageInformation/>
      }
    ]
  },
  {
    path: "/auth",
    element: <Auth />,
    errorElement: <Error/>
  },
  {
    path: "/error",
    element: <Error/>,
    errorElement: <Error/>
  }
]);


const App = () => {
  return (
    <div className="text-text font-inter">
      <RouterProvider router={routes} />
    </div>
  )
}

export default App