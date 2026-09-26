import { Search, ShoppingCart, User } from "lucide-react";

const navItems = ["Shop", "Categories", "New arrivals", "Featured"] as const;
const CART_COUNT = 0;

function Navbar() {
  return (
    <header className="sticky top-0 z-50 border-b border-line bg-surface">
      <div className="mx-auto flex max-w-6xl flex-wrap items-center gap-x-6 gap-y-3 px-4 py-3.5 sm:px-6 lg:px-8">
        <span className="shrink-0 text-sm font-semibold tracking-[0.32em] text-ink">
          NEXORA
        </span>

        <nav className="hidden min-w-0 items-center gap-6 lg:flex" aria-label="Primary">
          {navItems.map((item) => (
            <button
              key={item}
              type="button"
              className="text-sm text-muted transition hover:text-ink"
            >
              {item}
            </button>
          ))}
        </nav>

        <form
          className="order-3 min-w-0 w-full lg:order-none lg:ml-auto lg:w-56 xl:w-64"
          onSubmit={(event) => event.preventDefault()}
        >
          <label htmlFor="navbar-search" className="sr-only">
            Search products
          </label>
          <div className="flex w-full min-w-0 overflow-hidden rounded-full border border-line bg-paper focus-within:border-ink/30">
            <input
              id="navbar-search"
              type="search"
              placeholder="Search"
              className="min-w-0 flex-1 bg-transparent px-4 py-2 text-sm text-ink outline-none placeholder:text-muted"
            />
            <button
              type="submit"
              className="shrink-0 px-3 text-muted transition hover:text-ink"
              aria-label="Search"
            >
              <Search size={16} />
            </button>
          </div>
        </form>

        <div className="ml-auto flex shrink-0 items-center gap-1 lg:ml-0">
          <button
            type="button"
            className="inline-flex items-center gap-2 rounded-full px-3 py-2 text-sm text-ink transition hover:bg-paper"
          >
            <User size={18} />
            <span className="hidden sm:inline">Account</span>
          </button>
          <button
            type="button"
            className="relative inline-flex items-center gap-2 rounded-full px-3 py-2 text-sm text-ink transition hover:bg-paper"
            aria-label={`Cart, ${CART_COUNT} items`}
          >
            <span className="relative">
              <ShoppingCart size={18} />
              <span className="absolute -right-2 -top-2 flex h-4 min-w-4 items-center justify-center rounded-full bg-accent px-1 text-[10px] font-medium text-white">
                {CART_COUNT}
              </span>
            </span>
            <span className="hidden sm:inline">Cart</span>
          </button>
        </div>
      </div>
    </header>
  );
}

export default Navbar;
