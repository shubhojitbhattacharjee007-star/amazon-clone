import api from "./api";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

export interface AuthResponse {
  token: string;
  email: string;
  firstName: string;
  lastName: string;
  role: string;
}

export const login = async (
  credentials: LoginRequest
): Promise<AuthResponse> => {
  const response = await api.post("/auth/login", credentials);
  return response.data.data;
};

export const register = async (
  userData: RegisterRequest
): Promise<AuthResponse> => {
  const response = await api.post("/auth/register", userData);
  return response.data.data;
};