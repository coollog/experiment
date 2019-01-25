# experiment

```bash
$ kubectl apply -f k8s/game-server.yaml
$ kubectl port-forward service/game-server 8080:80
Open localhost:8080/browser
$ kubectl apply -f k8s/player.yaml
$ kubectl apply -f k8s/player2.yaml
$ kubectl apply -f k8s/player3.yaml
$ kubectl apply -f k8s/smart-player.yaml
```
