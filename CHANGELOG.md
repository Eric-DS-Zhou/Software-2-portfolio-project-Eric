# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [2026.02.03]

### Added

- Designed a `Student Grade Tracker` component
- Designed a `Household Expense Tracker` component
- Designed a `To-do List Tracker` component

[2026.02.03]: https://github.com/Eric-DS-Zhou/Software-2-portfolio-project-Eric/commits/v2026.02.03

## [2026.02.15]

### Changed

- Modified `Student Grade Tracker` component
- Modified `To-do List Tracker` componnent

## [2026.02.21]

### Added

- Designed a proof of concept for `HouseholdExpenseTracker` component

## [2026.03.05]

### Added

- Designed kernel and enhanced interfaces for `Household Expense Tracker` component
- Added `HouseholdExpense (Java record)` to store expense fields (`date, category, amount, note`)

## [2026.03.18]

### Added

- Designed abstract class for `HouseholdExpenseTracker` component
- Implemented all secondary methods in `HouseholdExpenseTracker`
- Implemented toString(), equals(), hashCode()

### Changed

- Updated the year precondition from `yyyy >= 1` to `yyyy >= 1000` so the component better reflects realistic calendar years.
- Changed the year-to-year boundary to yyyy >= 1001
- Changed the month-to-month boundary to yyyyMM >= 100101
- Updated the `HouseholdExpenseTracker` to align preconditions and method contracts with `HouseholdExpenseTrackerSecondary`
