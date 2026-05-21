import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VideoGame } from '../models/videogame.model';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class GameService {
  constructor(private api: ApiService) {}

  getAllGames(): Observable<VideoGame[]> {
    return this.api.get<VideoGame[]>('/games');
  }

  searchByCategory(category: string): Observable<VideoGame[]> {
    return this.api.get<VideoGame[]>(`/games/search?category=${encodeURIComponent(category)}`);
  }
}
