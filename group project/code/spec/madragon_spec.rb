require 'spec_helper'
require 'pp'

describe Madragon, :type => :class do

    before(:each) do

        file = "#{__dir__}/data/madragon.data"
        @start_board,
            @goal_board = DataImporter.import file

        @dragon = Madragon.new
    end

    describe '#solve' do

        it 'checks all possible k permutations' do

            pp @start_board.rows
            pp @goal_board.rows

            result = @dragon.solve @start_board, @goal_board

            expect(@start_board.rows.hash).to_not eq @goal_board.rows.hash
            expect(result).to_not eq false

            pp result
        end
    end

    describe '#generate_k_permutations' do

        it 'generates all k move permutations of R in S' do

            k = 4
            k_permutations = @dragon.generate_k_permutations @start_board, k

            expect(k_permutations.length).to eq ((@start_board.row_count+@start_board.col_count)*(((@start_board.row_count-1)+(@start_board.col_count-1))/2))**k
        end
    end

    describe '#generate_possible_moves' do

        it 'generates (m+n)*(((m-1)+(n-1))/2) moves' do

            possible_moves = @dragon.generate_possible_moves @start_board

            expect(possible_moves.length).to eq (@start_board.row_count+@start_board.col_count)*(((@start_board.row_count-1)+(@start_board.col_count-1))/2)
        end
    end
end