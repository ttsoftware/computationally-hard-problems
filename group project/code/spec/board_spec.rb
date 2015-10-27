require 'spec_helper'

describe Board, type: :class do

    before :each do
        file = "#{__dir__}/data/madragon.data"

        @start, @goal = DataImporter.import file
    end

    describe '#move' do

        it 'return a new board moved accordingly' do

            new_board = @start.move 6, 1

            pp @start.rows
            pp new_board.rows
        end
    end
end