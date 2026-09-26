function HeroVisual() {
  return (
    <div
      className="mx-auto grid aspect-[5/6] w-full max-w-[26rem] grid-cols-8 grid-rows-8 gap-2.5"
      aria-hidden="true"
    >
      <div className="col-span-5 row-span-5 rounded-card bg-surface" />
      <div className="col-span-3 row-span-3 rounded-card bg-ink" />
      <div className="col-span-3 row-span-2 rounded-card bg-accent" />
      <div className="col-span-3 row-span-3 rounded-card border border-line bg-surface" />
      <div className="col-span-2 row-span-3 rounded-card bg-ink" />
      <div className="col-span-3 row-span-3 rounded-card bg-surface" />
    </div>
  );
}

function Hero() {
  return (
    <section className="overflow-hidden">
      <div className="mx-auto grid max-w-6xl items-center gap-12 px-4 py-20 sm:px-6 lg:grid-cols-2 lg:gap-16 lg:px-8 lg:py-24">
        <div className="min-w-0">
          <p className="text-[11px] font-medium uppercase tracking-[0.28em] text-accent">
            The new way to shop
          </p>
          <h1 className="mt-5 text-4xl font-semibold leading-[1.05] tracking-tight text-ink sm:text-5xl lg:text-[3.5rem]">
            Everything you want.
            <br />
            In one place.
          </h1>
          <p className="mt-6 max-w-lg text-base leading-relaxed text-muted sm:text-lg">
            NEXORA is a modern marketplace for everyday essentials and
            considered finds — one quiet storefront to browse, compare, and buy
            with confidence.
          </p>
          <div className="mt-8 flex flex-wrap gap-3">
            <button
              type="button"
              className="rounded-full bg-ink px-6 py-3 text-sm font-medium text-white transition hover:bg-ink/90"
            >
              Shop now
            </button>
            <button
              type="button"
              className="rounded-full border border-line bg-surface px-6 py-3 text-sm font-medium text-ink transition hover:border-ink/25"
            >
              Explore categories
            </button>
          </div>
        </div>

        <div className="min-w-0">
          <HeroVisual />
        </div>
      </div>
    </section>
  );
}

export default Hero;
