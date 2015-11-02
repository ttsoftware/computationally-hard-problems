require 'spec_helper'

describe Board, type: :class do

    before :each do
        file = "#{__dir__}/data/madragon.data"
        @start, @goal = DataImporter.import file
    end

    describe '#initialize_clone' do

        it 'creates a deep copy of the object' do

            new_board = @start.clone

            new_board.move! 6, 1
            new_board.move! 1, 1

            expect(new_board.rows.hash).to_not eq @start.rows.hash
        end
    end

    describe '#move' do

        it 'moves the given object' do

            before_move = @start.rows.hash
            @start.move! 6, 1
            after_move = @start.rows.hash

            expect(before_move).to_not eq after_move
        end
    end
end