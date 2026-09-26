import type { CategoryItem } from "../data";

type CategoryCardProps = {
  category: CategoryItem;
};

function CategoryCard({ category }: CategoryCardProps) {
  const Icon = category.icon;

  return (
    <button
      type="button"
      className="group flex min-w-0 flex-col items-start rounded-card bg-surface px-5 py-6 text-left transition hover:bg-paper"
    >
      <span className="flex h-11 w-11 items-center justify-center rounded-card border border-line text-ink transition group-hover:border-accent/40 group-hover:text-accent">
        <Icon size={20} strokeWidth={1.6} />
      </span>
      <span className="mt-5 text-sm font-medium tracking-tight text-ink">
        {category.name}
      </span>
      <span className="mt-3 h-px w-6 bg-accent/70 transition-all group-hover:w-10" />
    </button>
  );
}

export default CategoryCard;
