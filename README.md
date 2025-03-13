# algorithm-pattern

자바 알고리즘과 패턴을 학습하기 위한 리포지터리

+) 자료구조 추가

### 알고리즘 참고 지식
- 브루트 포스 : 모든 경우의 수를 찾아 보는 것
  - 장) 모든 경우의 수를 탐색하므로 만족하는 값을 100% 찾아냄
  - 단) 조합 경우의 수가 많으면 자원이 매우 많이 필요함
- 백트래킹 : 노드의 유망성을 판단, 하위 범위는 탐색하지 않음
  > 백트래킹의 방법중 하나가 DFS 임. BFS(너비 우선 탐색)으로도 백트래킹 구현가능
- DFS(깊이 우선 탐색) : 한 경로를 끝까지 탐색, 백트래킹 기반 탐색 알고리즘
- BFS(너비 우선 탐색) : 가까운 노드부터 하나씩 탐색, 최단 경로 보장

|    상황    | DFS(Stack) | BFS(Queue) |
|:--------:|:----------:|:----------:|
|  최단 경로   |  X(보장안됨)   |   O(보장됨)   |
|  미로 탐색   |  출구가 깊은 곳  |  최단 경로 찾기  |
|   웹크롤링   |     X      |     O      |
| 경로탐색 AI  |     X      |     O      |
| 모든 경우 탐색 |     O      |     X      |
|  메모리 제한  |     O      |     X      |


### 구현한 디자인 패턴 목록

```markdown
- Strategy
- Proxy
- Adaptor
- Singleton
- Template
- Decorator
- Factory
- Observer
```


### 구현한 자료구조 목록

```markdown
- ArrayList
- SingleLinkedList
- DoubleLinkedList
- Stack
- Queue(using array)
- Queue(using LinkedList)
- Deque(using array)
- Deque(using LinkedList)
- Heap
- PriorityQueue
```

