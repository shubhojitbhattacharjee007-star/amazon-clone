import { featuredProducts } from "../data";
import ProductCard from "./ProductCard";

function FeaturedProducts() {
  return (
    <section>
      <div className="mx-auto max-w-6xl px-4 py-20 sm:px-6 lg:px-8">
        <div className="flex items-end justify-between gap-4">
          <div>
            <p className="text-[11px] font-medium uppercase tracking-[0.22em] text-muted">
              The edit
            </p>
            <h2 className="mt-3 text-2xl font-medium tracking-tight text-ink">
              Featured products
            </h2>
          </div>
          <p className="hidden max-w-xs text-right text-sm text-muted sm:block">
            A first look at the NEXORA edit
          </p>
        </div>
        <div className="mt-12 grid grid-cols-1 gap-x-6 gap-y-12 sm:grid-cols-2">
          {featuredProducts.map((product) => (
            <ProductCard key={product.id} product={product} />
          ))}
        </div>
      </div>
    </section>
  );
}

export default FeaturedProducts;
