import { benefits } from "../data";

function BenefitsSection() {
  return (
    <section>
      <div className="mx-auto max-w-6xl px-4 py-20 sm:px-6 lg:px-8">
        <h2 className="text-2xl font-medium tracking-tight text-ink">
          Why NEXORA
        </h2>
        <div className="mt-12 grid gap-10 sm:grid-cols-3 sm:gap-8">
          {benefits.map((benefit) => {
            const Icon = benefit.icon;

            return (
              <div key={benefit.id} className="min-w-0">
                <span className="flex h-10 w-10 items-center justify-center text-accent">
                  <Icon size={22} strokeWidth={1.5} />
                </span>
                <h3 className="mt-4 text-base font-medium tracking-tight text-ink">
                  {benefit.title}
                </h3>
                <p className="mt-2 text-sm leading-relaxed text-muted">
                  {benefit.description}
                </p>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
}

export default BenefitsSection;
