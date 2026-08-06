import { Search, ShoppingCart, User } from "lucide-react";

function Navbar() {
  return (
    <nav className="flex items-center justify-between bg-black px-6 py-4 text-white">
      
      {/* Logo */}
      <div className="text-2xl font-bold text-yellow-400">
        amazon
      </div>

      {/* Search Bar */}
      <div className="flex w-1/2">
        <input
          type="text"
          placeholder="Search products..."
          className="w-full rounded-l-md px-4 py-2 text-black outline-none"
        />
        <button className="rounded-r-md bg-yellow-400 px-4 text-black">
          <Search size={22} />
        </button>
      </div>

      {/* Right section */}
      <div className="flex items-center gap-6">
        <div className="flex items-center gap-2">
          <User size={22} />
          <span>Account</span>
        </div>

        <div className="flex items-center gap-2">
          <ShoppingCart size={22} />
          <span>Cart</span>
        </div>
      </div>

    </nav>
  );
}

export default Navbar;