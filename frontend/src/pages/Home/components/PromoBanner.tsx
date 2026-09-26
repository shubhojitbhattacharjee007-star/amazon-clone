function PromoBanner() {
  return (
    <section className="px-4 py-20 sm:px-6 lg:px-8">
      <div className="mx-auto max-w-6xl rounded-card bg-ink px-8 py-16 sm:px-12 lg:px-16 lg:py-20">
        <p className="text-[11px] font-medium uppercase tracking-[0.28em] text-white/50">
          NEXORA
        </p>
        <h2 className="mt-5 max-w-xl text-3xl font-medium tracking-tight text-white sm:text-4xl">
          Built for the way you shop.
        </h2>
        <p className="mt-4 max-w-lg text-sm leading-relaxed text-white/70 sm:text-base">
          Discover everyday essentials, premium picks, and everything in
          between.
        </p>
        <button
          type="button"
          className="mt-8 rounded-full bg-white px-6 py-3 text-sm font-medium text-ink transition hover:bg-paper"
        >
          Explore NEXORA
        </button>
      </div>
    </section>
  );
}

export default PromoBanner;
