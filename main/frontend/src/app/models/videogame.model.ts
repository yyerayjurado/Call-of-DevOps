export interface VideoGame {
  id?: number;
  title: string;
  description: string;
  category: string;
  salePrice: number;
  rentalPrice: number;
  stock: number;
  available: boolean;
  releaseDate?: string;
  coverImage?: string;
}
