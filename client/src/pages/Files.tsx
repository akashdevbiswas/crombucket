import Input from "../components/Input";

const Files = () => {
  return (
    <>
      <div className="w-full">
        <Input placeholder="Search" name="search" className="w-[30%]" focus={true} />
      </div>
    </>
  );
};

export default Files;
