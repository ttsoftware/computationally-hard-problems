require 'pp'

class Madragon

    # Returns a solution to given board start_board and goal_board using at most k moves.
    # Returns false if no solution exists using k moves.
    # @param [Board] start_board
    # @param [Board] goal_board
    # @param [Integer] max_k
    # @return [Object]
    def solve(start_board, goal_board, max_k=start_board.max_k)

        possible_moves = generate_possible_moves start_board

        k = 1
        solution = false
        until solution or k > max_k
            pp "possible moves: #{possible_moves.length}"
            pp "trying #{possible_moves.length**k} #{k}-length permutations"

            solution = solve_permutation(start_board, goal_board, possible_moves, k, [], 0, false)
            k = k+1
        end

        return solution
    end

    # Recursively solves the given boards
    # Returns false if no solution exists
    def solve_permutation(start_board,
                              goal_board,
                              possible_moves,
                              k,
                              index_positions,
                              index,
                              solution)

        0.upto(possible_moves.length - 1) do |index1|
            index_positions[index] = index1

            if index < k - 1
                unless solution
                    solution = solve_permutation(start_board, goal_board, possible_moves, k, index_positions, index + 1, solution)
                end
            else
                next_board = start_board.clone

                permutation = []
                0.upto(k - 1) do |index2|
                    move = possible_moves[index_positions[index2]]
                    next_board.move!(move[0], move[1])
                    permutation << move
                end

                solution = k, permutation if next_board == goal_board
            end
        end

        return solution
    end

    # Generates all possible moves on the board of size start_board.row_count*start_board.col_count
    # @param [Board] start_board
    # @return [Array]
    def generate_possible_moves(start_board)
        possible_moves = []

        # possible row moves
        start_board.row_count.times { |x|
            (start_board.col_count-1).times { |y|
                possible_moves << [x, y+1]
            }
        }

        # possible column moves
        start_board.col_count.times { |x|
            (start_board.row_count-1).times { |y|
                possible_moves << [start_board.row_count+x, y+1]
            }
        }

        return possible_moves
    end
end