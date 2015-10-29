class DataImporter

    def self.import(filename)

        File.open filename, 'r+' do |f|
            rows_count = f.readline.to_i
            cols_count = f.readline.to_i
            k = f.readline.to_i

            start = Board.new rows_count, cols_count, k
            goal = Board.new rows_count, cols_count, k

            rows_count.times do
                start.rows << f.readline.strip.split(//)
            end

            rows_count.times do
                goal.rows << f.readline.strip.split(//)
            end

            return start, goal
        end
    end
end