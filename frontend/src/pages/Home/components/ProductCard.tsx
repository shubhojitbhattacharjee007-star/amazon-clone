import { Star } from "lucide-react";
import type { ProductItem } from "../data";

type ProductCardProps = {
  product: ProductItem;
};

function ProductImage({ product }: { product: ProductItem }) {
  if (product.imageUrl) {
    return (
      <img
        src={product.imageUrl}
        alt={product.name}
        className="h-full w-full object-cover transition duration-500 group-hover:scale-[1.03]"
      />
    );
  }

  return (
    <div className="flex h-full w-full flex-col items-center justify-center gap-2 bg-paper px-4 text-center transition duration-500 group-hover:scale-[1.03]">
      <span className="text-[10px] uppercase tracking-[0.22em] text-muted">
        NEXORA
      </span>
      <span className="max-w-[12rem] text-sm font-medium tracking-tight text-ink">
        {product.name}
      </span>
    </div>
  );
}

function ProductCard({ product }: ProductCardProps) {
  return (
    <article className="group flex min-w-0 flex-col rounded-card bg-surface">
      <div className="aspect-[4/5] overflow-hidden rounded-card bg-paper">
        <ProductImage product={product} />
      </div>
      <div className="flex flex-1 flex-col px-1 pt-4 pb-1">
        <p className="text-[11px] uppercase tracking-[0.18em] text-muted">
          {product.category}
        </p>
        <h3 className="mt-2 text-base font-medium tracking-tight text-ink">
          {product.name}
        </h3>
        <div className="mt-3 flex items-center justify-between gap-3">
          <p className="text-sm text-ink">{product.price}</p>
          <p className="inline-flex items-center gap-1 text-xs text-muted">
            <Star size={12} className="fill-accent text-accent" />
            {product.rating.toFixed(1)}
          </p>
        </div>
        <button
          type="button"
          className="mt-5 w-full rounded-full border border-line px-4 py-2.5 text-sm font-medium text-ink transition hover:border-ink hover:bg-ink hover:text-white"
        >
          Add to cart
        </button>
      </div>
    </article>
  );
}

export default ProductCard;
