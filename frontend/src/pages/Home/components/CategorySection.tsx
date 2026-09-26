import { categories } from "../data";
import CategoryCard from "./CategoryCard";

function CategorySection() {
  return (
    <section>
      <div className="mx-auto max-w-6xl px-4 py-20 sm:px-6 lg:px-8">
        <p className="text-[11px] font-medium uppercase tracking-[0.22em] text-muted">
          Browse
        </p>
        <h2 className="mt-3 text-2xl font-medium tracking-tight text-ink">
          Shop by category
        </h2>
        <div className="mt-10 grid grid-cols-2 gap-3 sm:grid-cols-3 lg:grid-cols-6">
          {categories.map((category) => (
            <CategoryCard key={category.id} category={category} />
          ))}
        </div>
      </div>
    </section>
  );
}

export default CategorySection;
