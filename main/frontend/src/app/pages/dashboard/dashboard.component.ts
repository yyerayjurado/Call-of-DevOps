import { Component, OnInit } from '@angular/core';
import { VideoGame } from '../../models/videogame.model';
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  featuredGames: VideoGame[] = [];

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.gameService.getAllGames().subscribe(games => this.featuredGames = games.slice(0, 3));
  }
}
