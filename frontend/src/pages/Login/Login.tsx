import { useState } from "react";
import type { FormEvent } from "react";
import { Link, useNavigate } from "react-router-dom";
import { login } from "../../services/authService";

const Login = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setError("");
    setLoading(true);

    try {
      const authData = await login({
        email,
        password,
      });

      localStorage.setItem("token", authData.token);

      navigate("/");
    } catch (error: any) {
      setError(
        error.response?.data?.message ||
          "Login failed. Please check your email and password."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="min-h-[calc(100vh-80px)] bg-[#f7f4ef] px-6 py-16">
      <div className="mx-auto max-w-md">
        <div className="rounded-3xl border border-black/10 bg-white p-8 shadow-sm">
          <div className="mb-8 text-center">
            <p className="mb-2 text-xs font-semibold tracking-[0.2em] text-[#b85c45]">
              WELCOME BACK
            </p>

            <h1 className="text-3xl font-semibold tracking-tight text-[#171717]">
              Sign in to NEXORA
            </h1>

            <p className="mt-3 text-sm text-black/60">
              Access your account and continue shopping.
            </p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-5">
            <div>
              <label
                htmlFor="email"
                className="mb-2 block text-sm font-medium text-[#171717]"
              >
                Email
              </label>

              <input
                id="email"
                type="email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                placeholder="you@example.com"
                required
                className="w-full rounded-xl border border-black/15 px-4 py-3 text-sm outline-none transition focus:border-black"
              />
            </div>

            <div>
              <label
                htmlFor="password"
                className="mb-2 block text-sm font-medium text-[#171717]"
              >
                Password
              </label>

              <input
                id="password"
                type="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                placeholder="••••••••"
                required
                className="w-full rounded-xl border border-black/15 px-4 py-3 text-sm outline-none transition focus:border-black"
              />
            </div>

            {error && (
              <div className="rounded-xl bg-red-50 px-4 py-3 text-sm text-red-600">
                {error}
              </div>
            )}

            <button
              type="submit"
              disabled={loading}
              className="w-full rounded-full bg-[#171717] px-5 py-3 text-sm font-medium text-white transition hover:bg-black disabled:cursor-not-allowed disabled:opacity-60"
            >
              {loading ? "Signing in..." : "Sign in"}
            </button>
          </form>

          <p className="mt-6 text-center text-sm text-black/60">
            Don't have an account?{" "}
            <Link
              to="/register"
              className="font-medium text-[#171717] underline underline-offset-4"
            >
              Create one
            </Link>
          </p>
        </div>
      </div>
    </main>
  );
};

export default Login;