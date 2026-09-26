import type { LucideIcon } from "lucide-react";
import {
  BadgeCheck,
  Dumbbell,
  Home,
  ShieldCheck,
  Shirt,
  Smartphone,
  Sparkles,
  Truck,
  Watch,
} from "lucide-react";

export type CategoryItem = {
  id: string;
  name: string;
  icon: LucideIcon;
};

export type ProductItem = {
  id: string;
  name: string;
  category: string;
  price: string;
  rating: number;
  imageUrl?: string;
};

export type BenefitItem = {
  id: string;
  title: string;
  description: string;
  icon: LucideIcon;
};

export const categories: CategoryItem[] = [
  { id: "electronics", name: "Electronics", icon: Smartphone },
  { id: "fashion", name: "Fashion", icon: Shirt },
  { id: "home", name: "Home & Living", icon: Home },
  { id: "beauty", name: "Beauty", icon: Sparkles },
  { id: "sports", name: "Sports", icon: Dumbbell },
  { id: "accessories", name: "Accessories", icon: Watch },
];

export const featuredProducts: ProductItem[] = [
  {
    id: "headphones",
    name: "Wireless Headphones",
    category: "Electronics",
    price: "$129",
    rating: 4.8,
  },
  {
    id: "sneakers",
    name: "Minimal Sneakers",
    category: "Fashion",
    price: "$89",
    rating: 4.6,
  },
  {
    id: "watch",
    name: "Smart Watch",
    category: "Accessories",
    price: "$199",
    rating: 4.7,
  },
  {
    id: "backpack",
    name: "Everyday Backpack",
    category: "Fashion",
    price: "$74",
    rating: 4.5,
  },
];

export const benefits: BenefitItem[] = [
  {
    id: "curated",
    title: "Curated products",
    description:
      "Every listing is chosen for quality, so browsing feels considered rather than crowded.",
    icon: BadgeCheck,
  },
  {
    id: "secure",
    title: "Secure shopping",
    description:
      "Your account, orders, and payments stay protected with a calm, trusted checkout.",
    icon: ShieldCheck,
  },
  {
    id: "delivery",
    title: "Fast delivery",
    description:
      "From everyday essentials to premium picks, orders move quickly from cart to door.",
    icon: Truck,
  },
];
