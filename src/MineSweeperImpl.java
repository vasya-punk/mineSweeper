import org.apache.commons.lang3.StringUtils;

public class MineSweeperImpl implements MineSweeper {

    private String delimiter = "\n";
    private String charRegex = "([.*\n])+";
    private char[][] mineFieldMatrix;
    private static final char MINE = '*';
    private int width;
    private int height;
    private String[] rows;

    public void setMineField(String mineField) throws IllegalArgumentException
    {
        if(StringUtils.isNotEmpty(_mineField)){
            _mineField = null;
        }

        // simple validation
        if(!mineField.matches(charRegex))
        {
            throw new IllegalArgumentException();
        }

        rows = mineField.replaceAll("([.])","0").split(delimiter);

        if(rows.length == 0 || rows[0].length() == 0)
        {
            throw new IllegalArgumentException();
        }

        width = rows[0].length();
        height = rows.length;

        //check if mine field is rectangle
        String rowRegexp = "([.*]{"+width+"}\n)*";
        if (!mineField.matches(rowRegexp))
        {
            throw new IllegalArgumentException();
        }

        System.out.println("Matrix size : " + width + " x " + height);
        mineFieldMatrix = new char[height][width];
        for (int i = 0; i < height; i++)
        {
            mineFieldMatrix[i] = rows[i].toCharArray();
        }

        _mineField = mineField;
    }

    private String _mineField;
    public String getMineField(){
        return _mineField;
    }

    public String getHintField() throws IllegalStateException
    {
        if(StringUtils.isEmpty(getMineField()))
        {
            throw new IllegalStateException();
        }

        for (int j = 0; j < height; j++)
        {
            char[] chars = mineFieldMatrix[j];
            for (int i = 0; i < width; i++)
            {
                char c = chars[i];
                if(c == MINE)
                {
                    // top
                    appendHintMatrixItem(i-1, j-1);
                    appendHintMatrixItem(i, j-1);
                    appendHintMatrixItem(i+1, j-1);

                    // center
                    appendHintMatrixItem(i-1, j);
                    appendHintMatrixItem(i+1, j);

                    // bottom
                    appendHintMatrixItem(i-1, j+1);
                    appendHintMatrixItem(i, j+1);
                    appendHintMatrixItem(i+1, j+1);
                }
            }
        }

        StringBuffer result = new StringBuffer(width * height);
        for (char[] r : mineFieldMatrix)
        {
            result.append(r).append('\n');
        }

        return result.toString();
    }

    private void appendHintMatrixItem(int i, int j)
    {
        if(i >= 0 && i < width &&
           j >= 0 && j < height)
        {
            if(mineFieldMatrix[j][i] == MINE)
            {
                return;
            }

            mineFieldMatrix[j][i] = (char)((int)mineFieldMatrix[j][i] + 1);
        }
    }
}
