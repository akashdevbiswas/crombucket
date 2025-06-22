import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Auth from "./pages/Auth";
import Home from "./pages/Home";
import Error from "./pages/Error";

const routes = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/auth",
    element: <Auth />,
  },
  {
    path: "/error",
    element: <Error/>
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