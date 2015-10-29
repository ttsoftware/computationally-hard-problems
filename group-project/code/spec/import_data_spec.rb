require 'spec_helper'


describe DataImporter, type: :class do

    before :each do
        @importer = DataImporter.new
    end

    describe '#import' do
        it 'should convert raw input to data structure' do

            file = "#{__dir__}/data/madragon.data"

            start, goal = @importer.import file

            expect(start.row_count).to eq 5
            expect(goal.row_count).to eq 5
        end
    end
end