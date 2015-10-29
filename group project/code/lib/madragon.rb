require 'pp'

class Madragon

    def solve(start_board, goal_board, k=start_board.k)

        k_perm = generate_k_permutations(start_board, k)

        k_perm.each { |move_sequence|
            next_board = start_board.clone

            move_sequence.each { |move|
                next_board.move!(move[0], move[1])
            }

            return k, move_sequence if next_board == goal_board
        }

        return false
    end

    def generate_k_permutations(start_board, k)
        possible_moves = generate_possible_moves start_board
        return possible_moves.repeated_permutation(k).to_a
    end

    def generate_possible_moves(start_board)
        possible_moves = []

        start_board.row_count.times { |x|
            (start_board.col_count-1).times { |y|
                possible_moves << [x, y+1]
            }
        }

        start_board.col_count.times { |x|
            (start_board.row_count-1).times { |y|
                possible_moves << [start_board.row_count+x, y+1]
            }
        }

        return possible_moves
    end
end