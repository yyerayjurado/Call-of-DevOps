import { Component, OnInit } from '@angular/core';
import { GameService } from '../../services/game.service';
import { VideoGame } from '../../models/videogame.model';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  games: VideoGame[] = [];
  query = '';

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.loadGames();
  }

  loadGames(): void {
    this.gameService.getAllGames().subscribe(games => this.games = games);
  }

  search(): void {
    if (!this.query) {
      return this.loadGames();
    }
    this.gameService.searchByCategory(this.query).subscribe(games => this.games = games);
  }
}
