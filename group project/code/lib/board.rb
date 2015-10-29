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

    def move!(rowcol, steps)
        if rowcol < @row_count
            # we move a row
            @rows[rowcol] = @rows[rowcol].rotate steps
        else
            # we move a column
            col = []
            @rows.each { |r| col << r[rowcol-@col_count] }
            col.rotate! steps
            @rows.each_with_index { |r, i| r[rowcol-@col_count] = col[i] }
        end
    end

    def ==(other_board)
        return true if @rows.hash == other_board.rows.hash
    end

    private

    def initialize_clone(source)
        clone = super
        clone.rows = Marshal.load(Marshal.dump(source.rows))
        return clone
    end
end