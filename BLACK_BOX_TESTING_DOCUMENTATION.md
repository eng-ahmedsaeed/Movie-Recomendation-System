# Black Box Testing Documentation

## Overview
This document describes the black box testing strategy for the Movie Recommendation System, focusing on **Boundary Value Analysis** and **Equivalence Partitioning** techniques.

---

## Testing Techniques

### 1. Equivalence Partitioning (EP)
Equivalence partitioning divides input data into groups that behave similarly. Tests are designed to verify behavior for one representative from each partition.

### 2. Boundary Value Analysis (BV)
Boundary value analysis focuses on testing at the edges of input domains where errors are more likely to occur.

---

## Test Classes

### BlackBoxValidateMovieTest

#### Movie Name Validation
**Valid Equivalence Partitions:**
- EP1: Single or multiple words starting with capital letters

**Invalid Equivalence Partitions:**
- EP2: Null values, empty strings, lowercase starts, improper capitalization, special characters

**Boundary Values:**
- BV1: Single character movie name (minimum)
- BV2: Very long movie names (maximum)
- BV3: Names containing numbers

**Test Cases:**
| Test Case | Input | Expected | Type |
|-----------|-------|----------|------|
| Single capital word | "Avatar" | PASS | EP1 |
| Multiple capital words | "The Dark Knight" | PASS | EP1 |
| Null name | null | Exception | EP2 |
| Empty name | "" | Exception | EP2 |
| Lowercase start | "avatar" | Exception | EP2 |
| Single character | "A" | PASS | BV1 |
| Long name | "The Chronicles Of..." | PASS | BV2 |

#### Movie ID Validation
**Valid Equivalence Partitions:**
- EP3: Proper format (capital letters + 3 numeric digits)

**Invalid Equivalence Partitions:**
- EP4: Null, empty, wrong prefix, incorrect numeric format, duplicates

**Boundary Values:**
- BV4: Minimum numeric (000)
- BV5: Maximum numeric (999)
- BV6: Too few digits (< 3)
- BV7: Too many digits (> 3)
- BV8: Leading zeros

**Test Cases:**
| Test Case | Input | Expected | Type |
|-----------|-------|----------|------|
| Correct format | "TDK001" | PASS | EP3 |
| Wrong prefix | "XYZ001" | Exception | EP4 |
| Too few numbers | "TDK01" | Exception | BV6 |
| Too many numbers | "TDK0001" | Exception | BV7 |
| Minimum numeric | "TDK000" | PASS | BV4 |
| Maximum numeric | "TDK999" | PASS | BV5 |

---

### BlackBoxValidateUserTest

#### User ID Validation
**Valid Equivalence Partitions:**
- EP1: 9 characters starting with digit, alphanumeric content

**Invalid Equivalence Partitions:**
- EP2: Null, wrong length (< 9 or > 9), non-digit start, special characters, duplicates

**Boundary Values:**
- BV1: Exactly 9 characters (boundary)
- BV2: 8 characters (below)
- BV3: 10 characters (above)
- BV4: Maximum numeric (999999999)

**Test Cases:**
| Test Case | Input | Expected | Type |
|-----------|-------|----------|------|
| All digits | "123456789" | PASS | EP1 |
| Alphanumeric | "1A2B3C4D5" | PASS | EP1 |
| 8 characters | "12345678" | Exception | BV2 |
| 10 characters | "1234567890" | Exception | BV3 |
| Non-digit start | "A23456789" | Exception | EP2 |
| Special chars | "123@56789" | Exception | EP2 |
| Duplicate ID | (Same ID twice) | Exception | EP2 |

#### User Name Validation
**Valid Equivalence Partitions:**
- EP3: Single or multiple words with alphabetic characters and spaces

**Invalid Equivalence Partitions:**
- EP4: Null, empty, starts with space, contains numbers/special chars

**Boundary Values:**
- BV5: Single character (minimum)
- BV6: Long names with multiple words
- BV7: Single space between words

**Test Cases:**
| Test Case | Input | Expected | Type |
|-----------|-------|----------|------|
| Single word | "Ahmed" | PASS | EP3 |
| Multiple words | "Ahmed Saeed" | PASS | EP3 |
| Null name | null | Exception | EP4 |
| Empty name | "" | Exception | EP4 |
| Starts with space | " Ahmed" | Exception | EP4 |
| With numbers | "Ahmed123" | Exception | EP4 |
| Single character | "A" | PASS | BV5 |
| Long name | "Muhammad Ahmed Ibrahim..." | PASS | BV6 |

---

### BlackBoxDataBaseTest

#### Database Initialization
- EP1: Verifies empty ArrayList creation for both movies and users

#### Movie Insertion
**Valid Equivalence Partitions:**
- EP2: Single and multiple movie insertions

**Boundary Values:**
- BV1: Insert 1 movie (minimum)
- BV2: Insert 100 movies (large quantity)

#### SetMoviesDataBase
**Valid Equivalence Partitions:**
- EP3: Empty list, single movie, multiple movies

**Boundary Values:**
- BV3: Minimum database (single movie)

**Special Testing:**
- Verifies automatic sorting by movie ID occurs after set

#### SetUsersDataBase
**Valid Equivalence Partitions:**
- EP4: Empty list, single user, multiple users

**Boundary Values:**
- BV4: Minimum database (single user)

#### Genre Comparison
**Valid Equivalence Partitions:**
- EP5: Movies with common genres, no common genres

**Boundary Values:**
- BV5: Single genre per movie
- BV6: Multiple genres per movie

---

## Testing Strategy Summary

### Coverage Matrix
| Feature | EP | BV | Total Tests |
|---------|----|----|-------------|
| Movie Name | 5 | 3 | 8 |
| Movie ID | 2 | 5 | 7 |
| User ID | 2 | 4 | 6 |
| User Name | 4 | 3 | 7 |
| Database Ops | 4 | 2 | 6 |
| Genre Comparison | 2 | 2 | 4 |
| **TOTAL** | **19** | **19** | **38** |

---

## Running the Tests

### Using Maven
```bash
mvn test
```

### Running Specific Test Class
```bash
mvn test -Dtest=BlackBoxValidateMovieTest
mvn test -Dtest=BlackBoxValidateUserTest
mvn test -Dtest=BlackBoxDataBaseTest
```

### Running Specific Test Method
```bash
mvn test -Dtest=BlackBoxValidateMovieTest#testValidMovieName_SingleCapitalWord
```

---

## Expected Results

All 38 test cases should pass:
- ✓ Valid inputs return true or complete successfully
- ✓ Invalid inputs throw IllegalArgumentException
- ✓ Boundary values are handled correctly
- ✓ Equivalence partitions are properly separated

---

## Key Testing Insights

1. **Movie Name**: Must have proper capitalization for all words
2. **Movie ID**: Format is strict (prefix + exactly 3 digits, uniqueness required)
3. **User ID**: Length constraint of exactly 9 characters is critical
4. **User Name**: Alphabetic characters only (spaces allowed), no special chars
5. **Database**: Automatic sorting by ID occurs on set operation
6. **Genres**: Case-insensitive comparison is used for genre matching

---

## Notes
- All tests use JUnit 5 (Jupiter) framework
- @DisplayName annotations provide clear test descriptions
- Tests are organized in nested static classes for logical grouping
- Each test focuses on a single aspect of functionality (Single Responsibility)
