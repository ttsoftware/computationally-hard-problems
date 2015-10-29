require 'pp'

class Madragon

    # Returns a solution to given board start_board and goal_board using k moves.
    # Returns false if no solution exists using k moves.
    # @param [Board] start_board
    # @param [Board] goal_board
    # @param [Integer] k
    # @return [Object]
    def solve(start_board, goal_board, k=start_board.k)

        generate_k_permutations(start_board, k).each { |move_sequence|
            next_board = start_board.clone

            move_sequence.each { |move|
                next_board.move!(move[0], move[1])
            }

            return k, move_sequence if next_board == goal_board
        }

        return false
    end

    # Generates all k-length move-sets for given board start_board
    # @param [Board] start_board
    # @param [Integer] k
    # @return [Array]
    def generate_k_permutations(start_board, k)
        possible_moves = generate_possible_moves start_board

        pp "possible moves generated: #{possible_moves.length}"
        pp "trying to generate #{possible_moves.length**k} permutations"
        pp "should use around #{((possible_moves.length**k)*16)/1000000}MB memory"

        #return possible_moves.repeated_permutation(k).to_a
        return repeated_permutations(possible_moves, k, [], [], 0).to_a
    end

    def repeated_permutations(original_array,
                              length_of_each_permutation,
                              list_of_permutations,
                              index_positions,
                              index)
        0.upto(original_array.length - 1) do |index1|
            index_positions[index] = index1
            if index < length_of_each_permutation - 1
                repeated_permutations(original_array, length_of_each_permutation, list_of_permutations, index_positions, index + 1)
            else
                permutation = Array.new
                0.upto(length_of_each_permutation - 1) do |index2|
                    permutation[index2] = original_array[index_positions[index2]]
                end
                list_of_permutations.push(permutation)
            end
        end
        list_of_permutations
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