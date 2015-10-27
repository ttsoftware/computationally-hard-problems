class Board
    attr_accessor :row_count,
                  :col_count,
                  :k,
                  :rows

    def initialize(row_count, col_count, k, rows=[])
        @row_count=row_count
        @col_count=col_count
        @k = k
        @rows = rows
    end

    def move(rowcol, steps)

        new_rows = @rows.clone

        if rowcol < @row_count
            # we move a row
            new_rows[rowcol] = new_rows[rowcol].rotate steps
        else
            # we move a column
            col = []
            new_rows.each { |r| col << r[rowcol] }
            col.rotate! steps
            new_rows.each_with_index { |r, i| r[rowcol] = col[i] }
        end

        return Board.new @row_count, @col_count, @k, new_rows
    end

    def ==(other_board)
        @rows.each_with_index { |r, i| return false if other_board.rows[i] != r }
    end
end