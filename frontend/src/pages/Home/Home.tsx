import BenefitsSection from "./components/BenefitsSection";
import CategorySection from "./components/CategorySection";
import FeaturedProducts from "./components/FeaturedProducts";
import Hero from "./components/Hero";
import PromoBanner from "./components/PromoBanner";

function Home() {
  return (
    <div className="overflow-x-hidden">
      <Hero />
      <CategorySection />
      <FeaturedProducts />
      <PromoBanner />
      <BenefitsSection />
    </div>
  );
}

export default Home;
