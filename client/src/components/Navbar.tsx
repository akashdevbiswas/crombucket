
const Navbar = () => {
  return (
    <nav className="bg-primary w-full rounded-2xl p-4 flex items-center justify-between">
      <h1 className="text-xl font-medium md:text-3xl md:font-bold text-white">
        Crombucket
      </h1>
      <div className="flex items-center gap-2">
        <div className="bg-[#887AD3] text-white w-[40px] h-[40px] rounded-full flex flex-col items-center justify-center font-semibold text-lg">
          AB
        </div>
        <a className="text-white font-semibold text-2xl hidden md:inline-block">akashbiswas</a>
      </div>
    </nav>
  )
}

export default Navbar