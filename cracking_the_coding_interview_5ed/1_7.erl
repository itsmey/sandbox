-module(nullify).

-export([main/0]).

-define(SIZE_X, 17).
-define(SIZE_Y, 12).
-define(NULLS, 3).

%% Exported function

main() ->
  M = build_matrix(?SIZE_X, ?SIZE_Y, ?NULLS),
  print_matrix(M),
  NM = nullify(M),
  print_matrix(NM).

%% Internal functions

random(A, B) -> A + round(random:uniform() * (B - A)).    %% A..B

list_insert(L, Pos, Elem) ->
  lists:sublist(L, 1, Pos - 1) ++ [Elem] ++ lists:sublist(L, Pos + 1, length(L)).

build_matrix(NX, NY, Nulls) ->
  MakeList = fun() -> [random(1, 100) || _ <- lists:seq(1, NX)] end,
  M = [MakeList() || _ <- lists:seq(1, NY)],
  put_nulls(M, Nulls).

put_nulls(M, 0) ->
  M;

put_nulls(M, Nulls) ->
  R1 = random(1, ?SIZE_Y),
  R2 = random(1, ?SIZE_X),
  L = lists:nth(R1, M),
  NewL = list_insert(L, R2, 0),
  NewM = list_insert(M, R1, NewL),
  put_nulls(NewM, Nulls - 1).

print_matrix(M) ->
  lists:foreach(fun print_list/1, M),
  io:format("~n").

print_list(L) ->
  lists:foreach(fun(N) -> io:format("~4B", [N]) end, L),
  io:format("~n").

nullify(M) ->
  nullify(M, M, 1, 1).

nullify(_M, Result, ?SIZE_X, ?SIZE_Y + 1) ->
  Result;

nullify(M, Result, Column, ?SIZE_Y + 1) ->
  nullify(M, Result, Column + 1, 1);

nullify(M, Result, Column, Row) ->
  L = lists:nth(Row, M),
  N = lists:nth(Column, L),
  case N of
    0 -> nullify(M, update(Result, Column, Row), Column, Row + 1);
    _ -> nullify(M, Result, Column, Row + 1)
  end.

update(Result, Column, Row) ->
  NewR = list_insert(Result, Row, lists:duplicate(?SIZE_X, 0)),
  lists:map(fun(L) -> list_insert(L, Column, 0) end, NewR).
