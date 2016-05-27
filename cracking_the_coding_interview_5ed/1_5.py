data = ["aaabcccccdd",
        "abcd",
        "",
        "s",
        "aa",
        "aaaadefefwfytttyyyyyyy",
        "aaaadefefwfytttyyyyyy"]

def zip(string):
  if len(string) == 0:
    return "empty string"
  current = string[0]
  counter = 1
  result = ""
  for i in range(1, len(string)):
    if string[i] == current:
      counter = counter + 1
    else:
      result = result + current + str(counter)
      counter = 1
      current = string[i]
  result = result + current + str(counter)
  if len(result) > len(string):
    return string + ": discarded"
  else:
    return string + ": compressed to " + result

for string in data:
  print zip(string)
