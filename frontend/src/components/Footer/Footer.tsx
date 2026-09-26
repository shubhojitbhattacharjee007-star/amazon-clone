const shopLinks = ["New arrivals", "Featured", "Categories"] as const;
const accountLinks = ["Sign in", "Orders", "Cart"] as const;
const supportLinks = ["Help center", "Shipping", "Returns"] as const;
const legalLinks = ["Privacy", "Terms", "Returns"] as const;

function Footer() {
  return (
    <footer className="border-t border-line bg-surface">
      <div className="mx-auto grid max-w-6xl gap-10 px-4 py-16 sm:grid-cols-2 sm:px-6 lg:grid-cols-4 lg:px-8">
        <div>
          <p className="text-sm font-semibold tracking-[0.32em] text-ink">
            NEXORA
          </p>
          <p className="mt-3 max-w-sm text-sm leading-relaxed text-muted">
            Everything you need. One place.
          </p>
        </div>

        <div>
          <p className="text-[11px] font-medium uppercase tracking-[0.18em] text-muted">
            Shop
          </p>
          <ul className="mt-4 space-y-2 text-sm text-ink">
            {shopLinks.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>

        <div>
          <p className="text-[11px] font-medium uppercase tracking-[0.18em] text-muted">
            Account
          </p>
          <ul className="mt-4 space-y-2 text-sm text-ink">
            {accountLinks.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>

        <div>
          <p className="text-[11px] font-medium uppercase tracking-[0.18em] text-muted">
            Support
          </p>
          <ul className="mt-4 space-y-2 text-sm text-ink">
            {supportLinks.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>
      </div>

      <div className="border-t border-line">
        <div className="mx-auto flex max-w-6xl flex-col gap-3 px-4 py-5 text-xs text-muted sm:flex-row sm:items-center sm:justify-between sm:px-6 lg:px-8">
          <p>© {new Date().getFullYear()} NEXORA. All rights reserved.</p>
          <ul className="flex flex-wrap gap-x-5 gap-y-2">
            {legalLinks.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
