# DataBase Test Report

## Project: Movie Recommendation System
## Class Under Test: DataBase.java
## Date: December 19, 2025

---

## Latest Automated Test Run (2025-12-19)

- Command: `mvn test`
- Summary: Tests run: 421, Failures: 0, Errors: 2, Skipped: 0 — BUILD FAILURE
- Location of detailed reports: `target/surefire-reports`

- Failing tests (from Maven output):
     - `ProjectTm.BlackBoxValidateMovieTest$MovieIdValidationTests.testValidMovieId_MultipleLetters`
          - Exception: `java.lang.IllegalArgumentException: id must start with the starting capital letters of every word in movie name`
          - Source: `ProjectTm.ValidateMovie.ValidateMovieId` (ValidateMovie.java:42)
     - `ProjectTm.BlackBoxValidateMovieTest$MovieNameValidationTests.testValidMovieName_WithNumbers`
          - Exception: `java.lang.IllegalArgumentException: Movie title should be written such that every word starts with a capital letter`
          - Source: `ProjectTm.ValidateMovie.ValidateMovieName` (ValidateMovie.java:24)

Notes and suggestions:
- The failures are thrown from `ValidateMovie` validation methods. Inspect `src/main/java/ProjectTm/ValidateMovie.java` and the two failing test cases in `src/test/java/ProjectTm/BlackBoxValidateMovieTest.java` to determine whether the implementation or test expectations should change.
- Re-run `mvn test -DskipTests=false -e` for full stack traces if more detail is needed.


# UML CLASS DIAGRAM

```
┌─────────────────────────────────────────────────────────────┐
│                         DataBase                             │
├─────────────────────────────────────────────────────────────┤
│ - moviesDataBase: ArrayList<Movie>                          │
│ + usersDataBase: ArrayList<User>                            │
├─────────────────────────────────────────────────────────────┤
│ + DataBase()                                                │
│ + getMoviesDataBase(): ArrayList<Movie>                     │
│ + getUsersDataBase(): ArrayList<User>                       │
│ + setMoviesDataBase(movie: ArrayList<Movie>): void          │
│ + setUsersDataBase(user: ArrayList<User>): void             │
│ + insert(movie: Movie): void                                │
│ - movieSort(): void                                         │
│ + movieSearch(): void                                       │
│ + movieRecommend(): void                                    │
│ - genreCompare(T1: ArrayList<String>, T2: ArrayList<String>)│
│ + Clear(): void                                             │
└─────────────────────────────────────────────────────────────┘
           │                           │
           │ 1..*                      │ 1..*
           ▼                           ▼
┌─────────────────────┐    ┌─────────────────────────────────┐
│       Movie         │    │            User                  │
├─────────────────────┤    ├─────────────────────────────────┤
│ - id: String        │    │ - id: String                    │
│ - name: String      │    │ - name: String                  │
│ - genre: ArrayList  │    │ - searchedMovie: ArrayList      │
├─────────────────────┤    │ - recommendedMovies: ArrayList  │
│ + getId()           │    ├─────────────────────────────────┤
│ + getName()         │    │ + getId()                       │
│ + getGenre()        │    │ + getName()                     │
│ + setId()           │    │ + getSearchedMovie()            │
│ + setName()         │    │ + getRecommendedMovies()        │
│ + setGenre()        │    │ + setSearchedMovie()            │
└─────────────────────┘    │ + setRecommendedMovies()        │
                           └─────────────────────────────────┘
```

---

# CONTROL FLOW DIAGRAMS

## movieSearch() Method Flow

```
                    ┌─────────────────┐
                    │     START       │
                    └────────┬────────┘
                             │
                             ▼
              ┌──────────────────────────────┐
              │  i = 0                       │
              └──────────────┬───────────────┘
                             │
                             ▼
              ┌──────────────────────────────┐
         ┌────│ i < usersDataBase.size()?    │────┐
         │    └──────────────────────────────┘    │
         │ TRUE                              FALSE│
         ▼                                        ▼
┌─────────────────┐                    ┌─────────────────┐
│ reMovie = user  │                    │      END        │
│ .getSearched()  │                    └─────────────────┘
└────────┬────────┘
         │
         ▼
┌─────────────────────────┐
│  j = 0                  │
└────────┬────────────────┘
         │
         ▼
┌─────────────────────────┐
│ j < reMovie.size()?     │────────────┐
└────────┬────────────────┘       FALSE│
    TRUE │                             │
         ▼                             │
┌─────────────────────────┐            │
│ index = binarySearch()  │            │
└────────┬────────────────┘            │
         │                             │
         ▼                             │
   ┌─────────────┐                     │
   │ index < 0?  │                     │
   └──────┬──────┘                     │
     TRUE │ FALSE                      │
          │                            │
    ┌─────┴─────┐                      │
    ▼           ▼                      │
┌───────┐  ┌────────────┐              │
│m = new│  │m = movies  │              │
│Movie()│  │.get(index) │              │
│"Not   │  └─────┬──────┘              │
│found" │        │                     │
└───┬───┘        │                     │
    └────────────┼─────────────────────┤
                 ▼                     │
         ┌───────────────┐             │
         │ add m to list │             │
         └───────┬───────┘             │
                 │                     │
                 ▼                     │
         ┌───────────────┐             │
         │    j++        │◄────────────┘
         └───────┬───────┘
                 │
                 ▼
         ┌───────────────┐
         │    i++        │
         └───────┬───────┘
                 │
                 └────────► (loop back to i < usersDataBase.size())
```

---

## movieRecommend() Method Flow

```
                         ┌─────────────────┐
                         │     START       │
                         └────────┬────────┘
                                  │
                                  ▼
                   ┌──────────────────────────────┐
                   │  for each user (i loop)      │
                   └──────────────┬───────────────┘
                                  │
                                  ▼
                   ┌──────────────────────────────┐
              ┌────│ i < usersDataBase.size()?    │────┐
              │    └──────────────────────────────┘    │
              │ TRUE                              FALSE│
              ▼                                        ▼
    ┌──────────────────┐                    ┌─────────────────┐
    │ Create empty     │                    │      END        │
    │ RecommendedMovies│                    └─────────────────┘
    └─────────┬────────┘
              │
              ▼
    ┌──────────────────────────────┐
    │  for each searched movie (k) │
    └──────────────┬───────────────┘
                   │
                   ▼
         ┌─────────────────────────┐
    ┌────│ k < reMovie.size()?     │────┐
    │    └─────────────────────────┘    │
    │ TRUE                         FALSE│
    ▼                                   ▼
┌─────────────┐              ┌─────────────────────┐
│ Get Genre   │              │ Set recommendations │
│ of movie m  │              │ for user            │
└──────┬──────┘              └─────────────────────┘
       │
       ▼
┌─────────────────────────────────┐
│  for each movie in DB (j loop)  │
└──────────────┬──────────────────┘
               │
               ▼
      ┌─────────────────────────┐
 ┌────│ j < moviesDataBase.size()│────┐
 │    └─────────────────────────┘     │
 │ TRUE                          FALSE│
 ▼                                    │
┌───────────────────────────┐         │
│ flag = sameId(m, movie[j])│         │
└────────────┬──────────────┘         │
             │                        │
             ▼                        │
   ┌───────────────────────┐          │
   │ genreCompare(Genre,   │          │
   │   movie[j].getGenre())│          │
   │      && !flag ?       │          │
   └───────────┬───────────┘          │
          TRUE │ FALSE                │
               │                      │
         ┌─────┴─────┐                │
         ▼           ▼                │
  ┌─────────────┐ ┌──────┐            │
  │ Add movie[j]│ │ Skip │            │
  │ to recommend│ └───┬──┘            │
  └──────┬──────┘     │               │
         └────────────┴───────────────┤
                                      │
                      j++ ◄───────────┘
```

---

## genreCompare() Method Flow

```
         ┌─────────────────┐
         │     START       │
         │ genreCompare    │
         │ (T1, T2)        │
         └────────┬────────┘
                  │
                  ▼
         ┌─────────────────┐
         │    i = 0        │
         └────────┬────────┘
                  │
                  ▼
         ┌─────────────────────┐
    ┌────│  i < T1.size()?     │────┐
    │    └─────────────────────┘    │
    │ TRUE                     FALSE│
    ▼                               ▼
┌─────────────────┐        ┌─────────────────┐
│    j = 0        │        │  return FALSE   │
└────────┬────────┘        └─────────────────┘
         │
         ▼
┌─────────────────────┐
│  j < T2.size()?     │────────────┐
└────────┬────────────┘       FALSE│
    TRUE │                         │
         ▼                         │
┌───────────────────────────┐      │
│ T1.get(i).equalsIgnore    │      │
│ Case(T2.get(j))?          │      │
└───────────┬───────────────┘      │
       TRUE │ FALSE                │
            │                      │
      ┌─────┴─────┐                │
      ▼           ▼                │
┌───────────┐ ┌────────┐           │
│  return   │ │  j++   │───────────┤
│   TRUE    │ └────────┘           │
└───────────┘                      │
                                   │
                      i++ ◄────────┘
```

---

# DECISION TABLE

## movieRecommend() Decision Table

| Condition | Rule 1 | Rule 2 | Rule 3 | Rule 4 |
|-----------|--------|--------|--------|--------|
| genreCompare() returns TRUE | T | T | F | F |
| flag (same movie ID) | F | T | F | T |
| **Action** | | | | |
| Add to recommendations | ✅ | ❌ | ❌ | ❌ |

---

# COVERAGE MATRIX

## Methods & Branches Coverage

| Method | Line | Branch/Condition | Branch Test | Condition Test | Covered |
|--------|------|------------------|-------------|----------------|---------|
| `DataBase()` | 11-13 | Constructor | BR-1 | - | ✅ |
| `getMoviesDataBase()` | 15 | Getter | BR-1 | - | ✅ |
| `getUsersDataBase()` | 18 | Getter | BR-1 | - | ✅ |
| `setMoviesDataBase()` | 21-24 | Set + Sort | BR-2 | - | ✅ |
| `setUsersDataBase()` | 26-28 | Set users | BR-3 | - | ✅ |
| `insert()` | 30-32 | Add movie | BR-4 | - | ✅ |
| `movieSort()` | 36-40 | Collections.sort | BR-2 | - | ✅ |
| `movieSearch()` | 48 | `i < usersDataBase.size()` loop | BR-5, BR-6 | CC-1, CC-2 | ✅ |
| `movieSearch()` | 54 | `j < reMovie.size()` loop | BR-6, BR-7 | CC-3, CC-4 | ✅ |
| `movieSearch()` | 57 | `if (index < 0)` TRUE | BR-7 | CC-5 | ✅ |
| `movieSearch()` | 57 | `if (index < 0)` FALSE | BR-8 | CC-6 | ✅ |
| `movieRecommend()` | 72 | `i < usersDataBase.size()` loop | BR-9 | CC-7, CC-8 | ✅ |
| `movieRecommend()` | 75 | `k < reMovie.size()` loop | BR-10 | CC-9, CC-10 | ✅ |
| `movieRecommend()` | 79 | `j < moviesDataBase.size()` loop | BR-11 | CC-11, CC-12 | ✅ |
| `movieRecommend()` | 81 | `genreCompare && !flag` TT | BR-14 | CC-13 | ✅ |
| `movieRecommend()` | 81 | `genreCompare && !flag` TF | BR-13 | CC-14 | ✅ |
| `movieRecommend()` | 81 | `genreCompare && !flag` FT | BR-12 | CC-15 | ✅ |
| `movieRecommend()` | 81 | `genreCompare && !flag` FF | - | CC-16 | ✅ |
| `genreCompare()` | 91 | `i < T1.size()` loop | BR-15 | CC-17, CC-18 | ✅ |
| `genreCompare()` | 92 | `j < T2.size()` loop | BR-16 | CC-19, CC-20 | ✅ |
| `genreCompare()` | 93 | `equalsIgnoreCase` TRUE | BR-17 | CC-21 | ✅ |
| `genreCompare()` | 93 | `equalsIgnoreCase` FALSE | BR-18 | CC-22 | ✅ |
| `genreCompare()` | 97 | Return false (no match) | BR-18 | CC-24 | ✅ |
| `Clear()` | 99-102 | Set to null | BR-19 | - | ✅ |

## Condition Truth Table for Compound Conditions

### `genreCompare(Genre, moviesDataBase.get(j).getGenre()) && !(flag)`

| genreCompare | flag | !flag | Result | Test Case | Behavior |
|--------------|------|-------|--------|-----------|----------|
| TRUE | FALSE | TRUE | TRUE | CC-13 | Add recommendation |
| TRUE | TRUE | FALSE | FALSE | CC-14 | Skip (same movie) |
| FALSE | FALSE | TRUE | FALSE | CC-15 | Skip (no genre match) |
| FALSE | TRUE | FALSE | FALSE | CC-16 | Skip (both fail) |

---

# 1. BLACK BOX TESTING (Equivalence Partitioning & Boundary Value Analysis)

| Scen # | Scenario Description | Req # | Cond # | Test Data | Test Conditions/Steps | Expected Results/Comments | Post-Conditions | Actual Results | Pass/Fail |
|--------|---------------------|-------|--------|-----------|----------------------|---------------------------|-----------------|----------------|-----------|
| BB-1 | Empty database initialization | EP1 | - | new DataBase() | Create new DataBase instance | moviesDataBase and usersDataBase should be initialized as empty lists (size = 0) | Empty lists created | Lists initialized with size 0 | Pass |
| BB-2 | Insert 1 movie (boundary minimum) | BV1 | - | Movie(id="TDK001", name="The Dark Knight") | Insert single movie into empty database | Database size should be 1, movie ID matches inserted movie | 1 movie in database | size=1, id="TDK001" | Pass |
| BB-3 | Insert multiple movies | EP2 | - | Movie1(id="TDK001"), Movie2(id="INC002") | Insert 2 movies into database | Database size should be 2 | 2 movies in database | size=2 | Pass |
| BB-4 | Insert 100 movies (large quantity) | BV2 | - | 100 Movies (id="M000" to "M099") | Insert 100 movies in a loop | Database size should be 100 | 100 movies in database | size=100 | Pass |
| BB-5 | Set empty movie database | EP3 | - | Empty ArrayList<Movie> | setMoviesDataBase(empty list) | Database size should be 0 | Empty database | size=0 | Pass |
| BB-6 | Set minimum database (single movie) | BV3 | - | ArrayList with 1 Movie(id="ZZZ999") | setMoviesDataBase(list with 1 movie) | Database size should be 1 | 1 movie in database | size=1 | Pass |
| BB-7 | Set multiple movies | EP3 | - | ArrayList with 5 Movies (id="M000" to "M004") | setMoviesDataBase(list with 5 movies) | Database size should be 5 | 5 movies in database | size=5 | Pass |
| BB-8 | Verify sorting after set | EP3 | - | Movies: id="CCC001", "AAA001", "BBB001" | setMoviesDataBase with unsorted movies | Movies sorted alphabetically by ID: AAA001, BBB001, CCC001 | Sorted list | Order: AAA001, BBB001, CCC001 | Pass |
| BB-9 | Set empty users database | EP4 | - | Empty ArrayList<User> | setUsersDataBase(empty list) | Users database size should be 0 | Empty users list | size=0 | Pass |
| BB-10 | Set minimum user database | BV4 | - | ArrayList with 1 User(id="999999999", name="Test User") | setUsersDataBase(list with 1 user) | Users database size should be 1 | 1 user in database | size=1 | Pass |
| BB-11 | Set multiple users | EP4 | - | 10 Users (id="100000000" to "100000009") | setUsersDataBase(list with 10 users) | Users database size should be 10 | 10 users in database | size=10 | Pass |
| BB-12 | Movies with common genre | EP5 | - | Movie1(genre="Action","Crime"), Movie2(genre="Action","Thriller") | Add movies with overlapping genres | Both movies stored successfully (size >= 2) | Movies with shared genre stored | size=2 | Pass |
| BB-13 | Movies with different genres | EP5 | - | Movie1(genre="Action"), Movie2(genre="Romance") | Add movies with no overlapping genres | Both movies stored, no overlap | 2 distinct movies stored | size=2 | Pass |
| BB-14 | Movies with single genre (boundary) | BV5 | - | Movie1(genre="Action"), Movie2(genre="Action") | Add movies with exactly 1 genre each | Both movies stored with single genre | 2 movies with single genre | size=2 | Pass |
| BB-15 | Movies with multiple genres (boundary) | BV6 | - | Movie1(genre="Action","Sci-Fi","Adventure"), Movie2(genre="Adventure","Fantasy") | Add movies with 3 and 2 genres respectively | Both movies stored with multiple genres | 2 movies with multiple genres | size=2 | Pass |

---

# 2. BRANCH COVERAGE TESTING

| Scen # | Scenario Description | Req # | Cond # | Test Data | Test Conditions/Steps | Expected Results/Comments | Post-Conditions | Actual Results | Pass/Fail |
|--------|---------------------|-------|--------|-----------|----------------------|---------------------------|-----------------|----------------|-----------|
| BR-1 | Constructor initializes empty lists | - | C1 | new DataBase() | Create new DataBase instance | moviesDataBase != null, usersDataBase != null, both empty | Empty lists initialized | Lists not null, isEmpty=true | Pass |
| BR-2 | setMoviesDataBase sets and sorts movies | - | C2 | Movies: id="ZZ999","AA111" | setMoviesDataBase with unsorted movies | Movies sorted: AA111 first, ZZ999 second | Sorted movie list | AA111 at index 0, ZZ999 at index 1 | Pass |
| BR-3 | setUsersDataBase sets users | - | C3 | User(id="U001", name="John") | setUsersDataBase with 1 user | User stored with correct name | User accessible | name="John" | Pass |
| BR-4 | insert adds movie to database | - | C4 | Movie(id="M001", name="Test Movie", genre="Comedy") | insert(movie) | Database size=1, movie ID matches | Movie inserted | id="M001" | Pass |
| BR-5 | movieSearch - empty users (loop not entered) | - | C5 | usersDataBase=empty, moviesDataBase=empty | Call movieSearch() with no users | No exception, users list remains empty | Empty users list | isEmpty=true | Pass |
| BR-6 | movieSearch - user with no searched movies | - | C6 | User with empty searchedMovie list | Call movieSearch() | Inner loop not entered, no changes | Empty searched movies | searchedMovie.isEmpty()=true | Pass |
| BR-7 | movieSearch - movie not found (index < 0 TRUE) | - | C7 | moviesDataBase=[M001], User searches for "NONEXISTENT" | Call movieSearch() | Movie replaced with "Not found" movie | "Not found" in result | name="Not found" | Pass |
| BR-8 | movieSearch - movie found (index < 0 FALSE) | - | C8 | moviesDataBase=[M001:"Found Movie"], User searches for "M001" | Call movieSearch() | Movie found and returned with full details | Found movie returned | name="Found Movie" | Pass |
| BR-9 | movieRecommend - empty users (loop not entered) | - | C9 | usersDataBase=empty | Call movieRecommend() | No exception, users list remains empty | Empty users list | isEmpty=true | Pass |
| BR-10 | movieRecommend - user with no searched movies | - | C10 | User with empty searchedMovie list | Call movieRecommend() | Middle loop not entered, empty recommendations | Empty recommendations | recommendedMovies.isEmpty()=true | Pass |
| BR-11 | movieRecommend - empty movies database | - | C11 | moviesDataBase=empty, User has searched movie | Call movieRecommend() | Inner loop not entered, no recommendations | Empty recommendations | recommendedMovies.isEmpty()=true | Pass |
| BR-12 | movieRecommend - no matching genre | - | C12 | Searched="Action", Database has "Comedy" movie | Call movieRecommend() | genreCompare returns false, no recommendations | No recommendations | recommendedMovies.isEmpty()=true | Pass |
| BR-13 | movieRecommend - same movie ID | - | C13 | Searched id="M001", Only M001 in database | Call movieRecommend() | flag=true, movie not recommended to itself | No self-recommendation | recommendedMovies.isEmpty()=true | Pass |
| BR-14 | movieRecommend - matching genre, different ID | - | C14 | Searched id="M001" genre="Action", Database has M002 genre="Action" | Call movieRecommend() | genreCompare=true, flag=false, M002 recommended | Recommendation added | recommendedMovies contains M002 | Pass |
| BR-15 | genreCompare - empty T1 (outer loop not entered) | - | C15 | Searched movie has empty genre list | Call movieRecommend() | genreCompare returns false immediately | No recommendations | isEmpty()=true | Pass |
| BR-16 | genreCompare - empty T2 (inner loop not entered) | - | C16 | Database movie has empty genre list | Call movieRecommend() | Inner loop never executes, returns false | No recommendations | isEmpty()=true | Pass |
| BR-17 | genreCompare - match found on second genre | - | C17 | M001(genres="Action","Drama"), M002(genres="Comedy","Drama") | Call movieRecommend() | Match found on "Drama" | M002 recommended | recommendedMovies contains M002 | Pass |
| BR-18 | genreCompare - no match after checking all | - | C18 | M001(genres="Action","Drama"), M002(genres="Comedy","Horror") | Call movieRecommend() | No genre match found | No recommendations | isEmpty()=true | Pass |
| BR-19 | Clear sets lists to null | - | C19 | Database with movies and users | Call Clear() | Both lists set to null | Null references | moviesDataBase=null, usersDataBase=null | Pass |

---

# 3. CONDITION COVERAGE TESTING

| Scen # | Scenario Description | Req # | Cond # | Test Data | Test Conditions/Steps | Expected Results/Comments | Post-Conditions | Actual Results | Pass/Fail |
|--------|---------------------|-------|--------|-----------|----------------------|---------------------------|-----------------|----------------|-----------|
| CC-1 | movieSearch: i < usersDataBase.size() = FALSE | - | C1-F | usersDataBase=empty | Call movieSearch() | Loop condition false, loop skipped | No users processed | size=0 | Pass |
| CC-2 | movieSearch: i < usersDataBase.size() = TRUE | - | C1-T | usersDataBase=[1 user] | Call movieSearch() | Loop condition true, loop entered | User processed | size=1 | Pass |
| CC-3 | movieSearch: j < reMovie.size() = FALSE | - | C2-F | User with empty searchedMovie list | Call movieSearch() | Inner loop condition false, skipped | No movies searched | searchedMovie.isEmpty()=true | Pass |
| CC-4 | movieSearch: j < reMovie.size() = TRUE | - | C2-T | User with 1 searched movie | Call movieSearch() | Inner loop condition true, entered | Movie processed | searchedMovie.size()=1 | Pass |
| CC-5 | movieSearch: index < 0 = TRUE | - | C3-T | User searches for "NOTFOUND" | binarySearch returns negative | Movie not found, replaced with "Not found" | "Not found" result | name="Not found" | Pass |
| CC-6 | movieSearch: index < 0 = FALSE | - | C3-F | User searches for existing "M001" | binarySearch returns >= 0 | Movie found, full details returned | Found movie returned | name="Found Movie" | Pass |
| CC-7 | movieRecommend: i < usersDataBase.size() = FALSE | - | C4-F | usersDataBase=empty | Call movieRecommend() | Outer loop condition false | No users processed | isEmpty()=true | Pass |
| CC-8 | movieRecommend: i < usersDataBase.size() = TRUE | - | C4-T | usersDataBase=[1 user] | Call movieRecommend() | Outer loop condition true | User processed | size=1 | Pass |
| CC-9 | movieRecommend: k < reMovie.size() = FALSE | - | C5-F | User with empty searchedMovie | Call movieRecommend() | Middle loop condition false | No recommendations | recommendedMovies.isEmpty()=true | Pass |
| CC-10 | movieRecommend: k < reMovie.size() = TRUE | - | C5-T | User with 1 searched movie, Database has matching movie | Call movieRecommend() | Middle loop condition true | Recommendation processed | size=1 | Pass |
| CC-11 | movieRecommend: j < moviesDataBase.size() = FALSE | - | C6-F | moviesDataBase=empty | Call movieRecommend() | Inner loop condition false | No movies compared | isEmpty()=true | Pass |
| CC-12 | movieRecommend: j < moviesDataBase.size() = TRUE | - | C6-T | moviesDataBase=[1 movie] | Call movieRecommend() | Inner loop condition true | Movie compared | Not null | Pass |
| CC-13 | genreCompare=TRUE && !flag=TRUE | - | C7-TT | Searched M001("Action"), Database M002("Action") | genreCompare returns true, different ID | Both conditions true, recommendation added | M002 recommended | recommendedMovies contains M002 | Pass |
| CC-14 | genreCompare=TRUE && !flag=FALSE | - | C7-TF | Searched M001("Action"), Only M001 in database | genreCompare returns true, same ID | genre TRUE but flag FALSE (same movie) | No self-recommendation | isEmpty()=true | Pass |
| CC-15 | genreCompare=FALSE && !flag=TRUE | - | C7-FT | Searched M001("Action"), M002("Comedy") | genreCompare returns false, different ID | genreCompare FALSE, no recommendation | No recommendation | isEmpty()=true | Pass |
| CC-16 | genreCompare=FALSE && !flag=FALSE | - | C7-FF | M001("Action") searched with empty genre | genreCompare returns false, same ID | Both conditions false | No recommendation | isEmpty()=true | Pass |
| CC-17 | genreCompare: i < T1.size() = FALSE | - | C8-F | Searched movie has empty genre list | genreCompare T1 empty | Outer loop skipped, returns false | No match | isEmpty()=true | Pass |
| CC-18 | genreCompare: i < T1.size() = TRUE | - | C8-T | Searched movie has genre="Action" | genreCompare T1 has elements | Outer loop entered | Match possible | size=1 | Pass |
| CC-19 | genreCompare: j < T2.size() = FALSE | - | C9-F | Database movie has empty genre | genreCompare T2 empty | Inner loop skipped, returns false | No match | isEmpty()=true | Pass |
| CC-20 | genreCompare: j < T2.size() = TRUE | - | C9-T | Database movie has genre="Drama" | genreCompare T2 has elements | Inner loop entered | Comparison made | isEmpty()=true (different genre) | Pass |
| CC-21 | T1.get(i).equalsIgnoreCase(T2.get(j)) = TRUE | - | C10-T | M001("Action"), M002("action") | Genres match (case-insensitive) | Match found, returns true | Recommendation added | size=1 | Pass |
| CC-22 | T1.get(i).equalsIgnoreCase(T2.get(j)) = FALSE | - | C10-F | M001("Action"), M002("Comedy") | Genres don't match | No match, continues checking | No recommendation | isEmpty()=true | Pass |
| CC-23 | Multiple genres - match on second | - | C11 | M001("Action","Drama"), M002("Comedy","Drama") | Check multiple genres | Match found on "Drama" | M002 recommended | size=1 | Pass |
| CC-24 | Multiple genres - no match after all | - | C12 | M001("Action","Drama"), M002("Comedy","Horror") | Check all genres | No match found after all comparisons | No recommendation | isEmpty()=true | Pass |

---

# SUMMARY

| Test Type | Total Tests | Passed | Failed | Pass Rate |
|-----------|-------------|--------|--------|-----------|
| Black Box (EP & BVA) | 15 | 15 | 0 | 100% |
| Branch Coverage | 19 | 19 | 0 | 100% |
| Condition Coverage | 24 | 24 | 0 | 100% |
| **TOTAL** | **58** | **58** | **0** | **100%** |

---

## Test Methods Mapping

### BlackBoxDataBaseTest.java
| Test Method | Scen # |
|------------|--------|
| testDatabaseInitialization_Empty | BB-1 |
| testInsertMovie_OneMovie | BB-2 |
| testInsertMovie_Multiple | BB-3 |
| testInsertMovie_ManyMovies | BB-4 |
| testSetMoviesDatabase_Empty | BB-5 |
| testSetMoviesDatabase_Minimum | BB-6 |
| testSetMoviesDatabase_MultipleMovies | BB-7 |
| testSetMoviesDatabase_VerifySorting | BB-8 |
| testSetUsersDatabase_Empty | BB-9 |
| testSetUsersDatabase_Minimum | BB-10 |
| testSetUsersDatabase_MultipleUsers | BB-11 |
| testGenreComparison_CommonGenre | BB-12 |
| testGenreComparison_NoCommonGenre | BB-13 |
| testGenreComparison_SingleGenre | BB-14 |
| testGenreComparison_MultipleGenres | BB-15 |

### DataBaseBranchCoverageTest.java
| Test Method | Scen # |
|------------|--------|
| constructor_initializesEmptyLists | BR-1 |
| setMoviesDataBase_setsAndSortsMovies | BR-2 |
| setUsersDataBase_setsUsers | BR-3 |
| insert_addsMovieToDatabase | BR-4 |
| movieSearch_emptyUsersDataBase_noAction | BR-5 |
| movieSearch_userWithNoSearchedMovies_noAction | BR-6 |
| movieSearch_movieNotFound_returnsNotFoundMovie | BR-7 |
| movieSearch_movieFound_returnsFoundMovie | BR-8 |
| movieRecommend_emptyUsersDataBase_noAction | BR-9 |
| movieRecommend_userWithNoSearchedMovies_emptyRecommendations | BR-10 |
| movieRecommend_emptyMoviesDatabase_noRecommendations | BR-11 |
| movieRecommend_noMatchingGenre_noRecommendations | BR-12 |
| movieRecommend_sameMovieId_notRecommended | BR-13 |
| movieRecommend_matchingGenreDifferentId_isRecommended | BR-14 |
| genreCompare_emptyT1_returnsFalse | BR-15 |
| genreCompare_emptyT2_returnsFalse | BR-16 |
| genreCompare_matchFoundOnSecondGenre_returnsTrue | BR-17 |
| genreCompare_noMatchAfterCheckingAll_returnsFalse | BR-18 |
| clear_setsListsToNull | BR-19 |

### DataBaseConditionCoverageTest.java
| Test Method | Scen # |
|------------|--------|
| movieSearch_usersSize_conditionFalse | CC-1 |
| movieSearch_usersSize_conditionTrue | CC-2 |
| movieSearch_reMovieSize_conditionFalse | CC-3 |
| movieSearch_reMovieSize_conditionTrue | CC-4 |
| movieSearch_indexLessThanZero_conditionTrue | CC-5 |
| movieSearch_indexLessThanZero_conditionFalse | CC-6 |
| movieRecommend_usersSize_conditionFalse | CC-7 |
| movieRecommend_usersSize_conditionTrue | CC-8 |
| movieRecommend_reMovieSize_conditionFalse | CC-9 |
| movieRecommend_reMovieSize_conditionTrue | CC-10 |
| movieRecommend_moviesSize_conditionFalse | CC-11 |
| movieRecommend_moviesSize_conditionTrue | CC-12 |
| movieRecommend_genreMatchAndDifferentId_bothConditionsTrue | CC-13 |
| movieRecommend_genreMatchAndSameId_genreTrueFlagFalse | CC-14 |
| movieRecommend_noGenreMatchDifferentId_genreFalseFlagTrue | CC-15 |
| movieRecommend_noGenreMatchSameId_bothConditionsFalse | CC-16 |
| genreCompare_T1Size_conditionFalse | CC-17 |
| genreCompare_T1Size_conditionTrue | CC-18 |
| genreCompare_T2Size_conditionFalse | CC-19 |
| genreCompare_T2Size_conditionTrue | CC-20 |
| genreCompare_equalsIgnoreCase_conditionTrue | CC-21 |
| genreCompare_equalsIgnoreCase_conditionFalse | CC-22 |
| genreCompare_multipleGenres_matchFoundOnSecond | CC-23 |
| genreCompare_multipleGenres_noMatchAfterCheckingAll | CC-24 |

---

## Legend

| Abbreviation | Meaning |
|--------------|---------|
| EP | Equivalence Partitioning |
| BV | Boundary Value |
| BB | Black Box |
| BR | Branch Coverage |
| CC | Condition Coverage |
| T | TRUE |
| F | FALSE |
| TT | Both conditions TRUE |
| TF | First TRUE, Second FALSE |
| FT | First FALSE, Second TRUE |
| FF | Both conditions FALSE |

---

# COMPLETE BRANCH & CONDITION COVERAGE VERIFICATION

## All Branches Covered (19 Tests)

| # | Method | Branch Description | Test | Status |
|---|--------|-------------------|------|--------|
| 1 | constructor | Initialize lists | BR-1 | ✅ |
| 2 | setMoviesDataBase | Set and sort | BR-2 | ✅ |
| 3 | setUsersDataBase | Set users | BR-3 | ✅ |
| 4 | insert | Add movie | BR-4 | ✅ |
| 5 | movieSearch | Outer loop NOT entered | BR-5 | ✅ |
| 6 | movieSearch | Inner loop NOT entered | BR-6 | ✅ |
| 7 | movieSearch | index < 0 TRUE | BR-7 | ✅ |
| 8 | movieSearch | index < 0 FALSE | BR-8 | ✅ |
| 9 | movieRecommend | Outer loop NOT entered | BR-9 | ✅ |
| 10 | movieRecommend | Middle loop NOT entered | BR-10 | ✅ |
| 11 | movieRecommend | Inner loop NOT entered | BR-11 | ✅ |
| 12 | movieRecommend | genreCompare=F, no recommend | BR-12 | ✅ |
| 13 | movieRecommend | flag=T, skip same movie | BR-13 | ✅ |
| 14 | movieRecommend | Both conditions T, recommend | BR-14 | ✅ |
| 15 | genreCompare | T1 empty, return false | BR-15 | ✅ |
| 16 | genreCompare | T2 empty, return false | BR-16 | ✅ |
| 17 | genreCompare | Match on 2nd genre | BR-17 | ✅ |
| 18 | genreCompare | No match after all | BR-18 | ✅ |
| 19 | Clear | Set null | BR-19 | ✅ |

## All Conditions Covered (24 Tests)

| # | Method | Condition | TRUE Test | FALSE Test | Status |
|---|--------|-----------|-----------|------------|--------|
| 1 | movieSearch | `i < usersDataBase.size()` | CC-2 | CC-1 | ✅ |
| 2 | movieSearch | `j < reMovie.size()` | CC-4 | CC-3 | ✅ |
| 3 | movieSearch | `index < 0` | CC-5 | CC-6 | ✅ |
| 4 | movieRecommend | `i < usersDataBase.size()` | CC-8 | CC-7 | ✅ |
| 5 | movieRecommend | `k < reMovie.size()` | CC-10 | CC-9 | ✅ |
| 6 | movieRecommend | `j < moviesDataBase.size()` | CC-12 | CC-11 | ✅ |
| 7 | movieRecommend | `genreCompare()` | CC-13,18,21 | CC-15,16,22 | ✅ |
| 8 | movieRecommend | `!flag` | CC-13,15 | CC-14,16 | ✅ |
| 9 | genreCompare | `i < T1.size()` | CC-18 | CC-17 | ✅ |
| 10 | genreCompare | `j < T2.size()` | CC-20 | CC-19 | ✅ |
| 11 | genreCompare | `equalsIgnoreCase()` | CC-21 | CC-22 | ✅ |
| 12 | genreCompare | Multiple genre scenarios | CC-23 | CC-24 | ✅ |

---

## Execution Results

```
Tests run: 43, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

**All 43 white-box tests (19 Branch + 24 Condition) PASSED ✅**
