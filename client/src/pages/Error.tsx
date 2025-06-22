import { Link } from "react-router-dom";

const Error = () => {
  return (
    <div className="min-h-screen">
      Some error occured.
      <Link className="text-secondary" to="/">Back to Home</Link>
    </div>
  );
};

export default Error;
